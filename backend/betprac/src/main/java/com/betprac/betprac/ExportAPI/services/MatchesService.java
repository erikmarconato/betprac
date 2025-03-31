package com.betprac.betprac.ExportAPI.services;

import com.betprac.betprac.ExportAPI.dtos.MatchesDto;
import com.betprac.betprac.ExportAPI.dtos.OddsDto;
import com.betprac.betprac.FootballAPI.entities.MatchesEntity;
import com.betprac.betprac.FootballAPI.repositories.MatchesRepository;
import com.betprac.betprac.FootballAPI.repositories.OddsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchesService {

    @Autowired
    MatchesRepository matchesRepository;

    @Autowired
    OddsRepository oddsRepository;

    public List<MatchesDto> listMatchesByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59);

        List<MatchesEntity> matchesEntityList = matchesRepository.findByMatchDateBetween(startOfDay, endOfDay);

        return matchesEntityList.stream().map(matchesEntity -> {
            List<OddsDto> filteredOdds = oddsRepository.findByMatchId(matchesEntity.getId()).stream()
                    .filter(odds -> "Match Winner".equals(odds.getBetType()))
                    .map(odds -> new OddsDto(odds.getBetType(), odds.getValue(), odds.getOdd()))
                    .collect(Collectors.toList());

            return new MatchesDto(
                    matchesEntity.getId(),
                    matchesEntity.getFixtureId(),
                    matchesEntity.getMatchDate(),
                    matchesEntity.getStatusMatch(),
                    matchesEntity.getLeague(),
                    matchesEntity.getCountry(),
                    matchesEntity.getHomeTeam(),
                    matchesEntity.getAwayTeam(),
                    matchesEntity.getImgHomeTeam(),
                    matchesEntity.getImgAwayTeam(),
                    matchesEntity.getStatisticsUploaded(),
                    matchesEntity.getOddsUploaded(),
                    filteredOdds
            );
        }).collect(Collectors.toList());
    }
}
