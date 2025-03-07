package com.betprac.betprac.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fixtureId", unique = true)
    private Long fixtureId;

    @Column(name = "matchDate")
    private LocalDateTime matchDate;

    @Column(name = "statusMatch")
    private String statusMatch;

    @Column(name = "league")
    private String league;

    @Column(name = "country")
    private String country;

    @Column(name = "homeTeam")
    private String homeTeam;

    @Column(name = "awayTeam")
    private String awayTeam;

    @Column(name = "imgHomeTeam")
    private String imgHomeTeam;

    @Column(name = "imgAwayTeam")
    private String imgAwayTeam;

    public MatchesEntity(Long fixtureId, LocalDateTime matchDate, String statusMatch, String league,
                         String country, String homeTeam, String awayTeam, String imgHomeTeam, String imgAwayTeam) {
        this.fixtureId = fixtureId;
        this.matchDate = matchDate;
        this.statusMatch = statusMatch;
        this.league = league;
        this.country = country;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.imgHomeTeam = imgHomeTeam;
        this.imgAwayTeam = imgAwayTeam;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public void setStatusMatch(String statusMatch) {
        this.statusMatch = statusMatch;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setImgHomeTeam(String imgHomeTeam) {
        this.imgHomeTeam = imgHomeTeam;
    }

    public void setImgAwayTeam(String imgAwayTeam) {
        this.imgAwayTeam = imgAwayTeam;
    }

}
