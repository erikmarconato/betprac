package com.betprac.betprac.services;

import com.betprac.betprac.entities.MatchesEntity;
import com.betprac.betprac.entities.OddsEntity;
import com.betprac.betprac.repositories.MatchesRepository;
import com.betprac.betprac.repositories.OddsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

@Service
public class OddsService {

    private final RestTemplate restTemplate;
    private final MatchesRepository matchesRepository;
    private final OddsRepository oddsRepository;
    private final ObjectMapper objectMapper;

    @Value("${api.football.key}")
    private String apiKey;

    @Value("${api.football.host}")
    private String apiHost;

    public OddsService(RestTemplate restTemplate, MatchesRepository matchesRepository, OddsRepository oddsRepository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.matchesRepository = matchesRepository;
        this.oddsRepository = oddsRepository;
        this.objectMapper = objectMapper;
    }

    @Scheduled(cron = "20 43 08 * * ?")
    public void fetchOddsForMatches() {
        List<MatchesEntity> matches = matchesRepository.findByStatusMatchAndOddsUploaded("NS", false);

        if (matches.isEmpty()) {
            return;
        }

        String url = "https://api-football-v1.p.rapidapi.com/v3/odds?date=" + LocalDate.now() + "&page=1&bookmaker=8";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", apiKey);
        headers.set("x-rapidapi-host", apiHost);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        int totalPages = getTotalPagesFromResponse(response.getBody());

        for (int page = 1; page <= totalPages; page++) {
            String pageUrl = "https://api-football-v1.p.rapidapi.com/v3/odds?date=" + LocalDate.now() + "&page=" + page + "&bookmaker=8";
            ResponseEntity<String> pageResponse = restTemplate.exchange(pageUrl, HttpMethod.GET, entity, String.class);

            processOddsForMatches(pageResponse.getBody(), matches);
        }

        for (MatchesEntity match : matches) {
            match.markOddsAsUploaded(true);
            matchesRepository.save(match);
        }
    }

    private int getTotalPagesFromResponse(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("paging").path("total").asInt();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void processOddsForMatches(String responseBody, List<MatchesEntity> matches) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode responseArray = root.path("response");

            if (responseArray.isArray() && responseArray.size() > 0) {
                for (JsonNode leagueNode : responseArray) {
                    JsonNode fixtureNode = leagueNode.path("fixture");
                    Long fixtureId = fixtureNode.path("id").asLong();

                    for (MatchesEntity match : matches) {
                        if (!fixtureId.equals(match.getFixtureId())) {
                            continue;
                        }

                        JsonNode bookmakersArray = leagueNode.path("bookmakers");
                        for (JsonNode bookmakerNode : bookmakersArray) {
                            JsonNode betsArray = bookmakerNode.path("bets");

                            for (JsonNode betNode : betsArray) {
                                String betType = betNode.path("name").asText();
                                JsonNode valuesArray = betNode.path("values");

                                for (JsonNode valueNode : valuesArray) {
                                    String outcome = valueNode.path("value").asText();
                                    double odd = valueNode.path("odd").asDouble();

                                    OddsEntity oddsEntity = new OddsEntity();
                                    oddsEntity.setMatch(match);
                                    oddsEntity.setBetType(betType);
                                    oddsEntity.setValue(outcome);
                                    oddsEntity.setOdd(odd);

                                    oddsRepository.save(oddsEntity);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
