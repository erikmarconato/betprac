package com.betprac.betprac.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "matchStatistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchesEntity match;

    @Column(name = "homeWinner")
    private Boolean homeWinner;

    @Column(name = "awayWinner")
    private Boolean awayWinner;

    @Column(name = "homeGoals")
    private int homeGoals;

    @Column(name = "awayGoals")
    private int awayGoals;

    @Column(name = "homeShotsOnGoal")
    private int homeShotsOnGoal;

    @Column(name = "homeTotalShots")
    private int homeTotalShots;

    @Column(name = "awayShotsOnGoal")
    private int awayShotsOnGoal;

    @Column(name = "awayTotalShots")
    private int awayTotalShots;

    @Column(name = "homeFouls")
    private int homeFouls;

    @Column(name = "awayFouls")
    private int awayFouls;

    @Column(name = "homeCorner")
    private int homeCorner;

    @Column(name = "awayCorner")
    private int awayCorner;

    @Column(name = "homeOffside")
    private int homeOffside;

    @Column(name = "awayOffside")
    private int awayOffside;

    @Column(name = "homeYellowCard")
    private int homeYellowCard;

    @Column(name = "awayYellowCard")
    private int awayYellowCard;

    @Column(name = "homeRedCard")
    private int homeRedCard;

    @Column(name = "awayRedCard")
    private int awayRedCard;

    @Column(name = "homeGoalkeeperSave")
    private int homeGoalkeeperSave;

    @Column(name = "awayGoalkeeperSave")
    private int awayGoalkeeperSave;

    @Column(name = "homeTotalPasses")
    private int homeTotalPasses;

    @Column(name = "awayTotalPasses")
    private int awayTotalPasses;

    public void setHomeShotsOnGoal(int homeShotsOnGoal) {
        this.homeShotsOnGoal = homeShotsOnGoal;
    }

    public void setAwayShotsOnGoal(int awayShotsOnGoal) {
        this.awayShotsOnGoal = awayShotsOnGoal;
    }

    public void setHomeTotalShots(int homeTotalShots) {
        this.homeTotalShots = homeTotalShots;
    }

    public void setAwayTotalShots(int awayTotalShots) {
        this.awayTotalShots = awayTotalShots;
    }

    public void setHomeFouls(int homeFouls) {
        this.homeFouls = homeFouls;
    }

    public void setAwayFouls(int awayFouls) {
        this.awayFouls = awayFouls;
    }

    public void setHomeCorner(int homeCorner) {
        this.homeCorner = homeCorner;
    }

    public void setAwayCorner(int awayCorner) {
        this.awayCorner = awayCorner;
    }

    public void setHomeOffside(int homeOffside) {
        this.homeOffside = homeOffside;
    }

    public void setAwayOffside(int awayOffside) {
        this.awayOffside = awayOffside;
    }

    public void setHomeYellowCard(int homeYellowCard) {
        this.homeYellowCard = homeYellowCard;
    }

    public void setAwayYellowCard(int awayYellowCard) {
        this.awayYellowCard = awayYellowCard;
    }

    public void setHomeRedCard(int homeRedCard) {
        this.homeRedCard = homeRedCard;
    }

    public void setAwayRedCard(int awayRedCard) {
        this.awayRedCard = awayRedCard;
    }

    public void setHomeGoalkeeperSave(int homeGoalkeeperSave) {
        this.homeGoalkeeperSave = homeGoalkeeperSave;
    }

    public void setAwayGoalkeeperSave(int awayGoalkeeperSave) {
        this.awayGoalkeeperSave = awayGoalkeeperSave;
    }

    public void setHomeTotalPasses(int homeTotalPasses) {
        this.homeTotalPasses = homeTotalPasses;
    }

    public void setAwayTotalPasses(int awayTotalPasses) {
        this.awayTotalPasses = awayTotalPasses;
    }

    public void setMatch(MatchesEntity match) {
        this.match = match;
    }

    public void setHomeWinner(boolean homeWinner) {
        this.homeWinner = homeWinner;
    }

    public void setAwayWinner(boolean awayWinner) {
        this.awayWinner = awayWinner;
    }


}
