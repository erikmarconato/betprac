package com.betprac.betprac.services;

import com.betprac.betprac.entities.MatchesEntity;
import com.betprac.betprac.repositories.MatchesRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchesRepository matchesRepository;
    private final RestTemplate restTemplate;

    private static final List<Integer> MAIN_LEAGUES = List.of(
            39,  // Premier League (Inglaterra)
            140, // La Liga (Espanha)
            78,  // Bundesliga (Alemanha)
            135, // Serie A (Itália)
            61,  // Ligue 1 (França)
            88,  // Eredivisie (Países Baixos)
            94,  // Primeira Liga (Portugal)
            203  // Süper Lig (Turquia)
    );

    public MatchService(MatchesRepository matchesRepository, RestTemplate restTemplate) {
        this.matchesRepository = matchesRepository;
        this.restTemplate = restTemplate;
    }

    //@Scheduled(cron = "0 0 4 * * ?")  // Executa todos os dias às 4:00 AM
    @Scheduled(cron = "0 55 0 * * ?")
    public void fetchAndSaveMatches() {

        // Calcular a data do dia seguinte
        LocalDate tomorrow = LocalDate.now().plusDays(1); // Adiciona 1 dia à data atual
        String formattedDate = tomorrow.format(DateTimeFormatter.ISO_DATE); // Formata a data no formato "yyyy-MM-dd"

        // URL da API com a data de um dia seguinte
        String url = "https://api-football-v1.p.rapidapi.com/v3/fixtures?date=" + formattedDate;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", "23f426539amsh380613e9e0964f5p1f3f99jsn63e443ca7939");
        headers.set("x-rapidapi-host", "api-football-v1.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());
                JsonNode matchesArray = jsonResponse.get("response");

                if (matchesArray != null) {
                    for (JsonNode match : matchesArray) {

                        // Pegando informações da liga
                        JsonNode leagueObj = match.get("league");
                        int leagueId = leagueObj.get("id").asInt();

                        // Filtrando apenas as principais ligas
                        if (!MAIN_LEAGUES.contains(leagueId)) {
                            continue;
                        }

                        // Pegando informações da partida
                        JsonNode fixture = match.get("fixture");
                        long fixtureId = fixture.get("id").asLong();
                        LocalDateTime matchDate = LocalDateTime.parse(
                                fixture.get("date").asText(),
                                DateTimeFormatter.ISO_DATE_TIME
                        );

                        // Ajuste para o fuso horário UTC-3
                        matchDate = matchDate.atOffset(ZoneOffset.UTC).plusHours(-3).toLocalDateTime();

                        JsonNode status = fixture.get("status");
                        String matchStatus = status.get("short").asText();

                        // Pegando informações dos times
                        JsonNode teams = match.get("teams");
                        JsonNode homeTeam = teams.get("home");
                        JsonNode awayTeam = teams.get("away");

                        // Pegando informações básicas
                        String leagueName = leagueObj.get("name").asText();
                        String country = leagueObj.get("country").asText();
                        String homeTeamName = homeTeam.get("name").asText();
                        String awayTeamName = awayTeam.get("name").asText();
                        String imgHomeTeam = homeTeam.get("logo").asText();
                        String imgAwayTeam = awayTeam.get("logo").asText();

                        // Verifica se a partida já existe no banco
                        Optional<MatchesEntity> existingMatch = matchesRepository.findByFixtureId(fixtureId);

                        MatchesEntity matchEntity;
                        if (existingMatch.isPresent()) {
                            // Atualiza os dados existentes
                            matchEntity = existingMatch.get();
                            matchEntity.setMatchDate(matchDate);
                            matchEntity.setStatusMatch(matchStatus);
                            matchEntity.setLeague(leagueName);
                            matchEntity.setCountry(country);
                            matchEntity.setHomeTeam(homeTeamName);
                            matchEntity.setAwayTeam(awayTeamName);
                            matchEntity.setImgHomeTeam(imgHomeTeam);
                            matchEntity.setImgAwayTeam(imgAwayTeam);
                        } else {
                            matchEntity = new MatchesEntity(
                                    fixtureId, matchDate, matchStatus, leagueName, country,
                                    homeTeamName, awayTeamName, imgHomeTeam, imgAwayTeam
                            );
                        }

                        matchesRepository.save(matchEntity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
