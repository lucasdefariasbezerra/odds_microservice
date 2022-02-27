package com.lucasbezerra.oddsproject.model;

import com.lucasbezerra.oddsproject.model.dto.MatchesPayloadDTO;

import javax.persistence.*;

@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="team_home", nullable = false)
    private Team teamHome;

    @ManyToOne
    @JoinColumn(name="team_away", nullable = false)
    private Team teamAway;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private SeasonGroup seasonGroup;

    @Column(name = "score_home")
    private Integer scoreHome;

    @Column(name = "score_away")
    private Integer scoreAway;

    @Column(name = "match_date")
    private Long matchDate;

    @Column(name = "round")
    private Integer round;

    public Match() {
    }

    public Match(Team teamHome, Team teamAway, SeasonGroup seasonGroup, Long matchDate, Integer round) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.seasonGroup = seasonGroup;
        this.scoreHome = 0;
        this.scoreAway = 0;
        this.matchDate = matchDate;
        this.round = round;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }

    public SeasonGroup getSeasonGroup() {
        return seasonGroup;
    }

    public void setSeasonGroup(SeasonGroup seasonGroup) {
        this.seasonGroup = seasonGroup;
    }

    public Integer getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(Integer scoreHome) {
        this.scoreHome = scoreHome;
    }

    public Integer getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(Integer scoreAway) {
        this.scoreAway = scoreAway;
    }

    public Long getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Long matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "Match{" +
                "teamHome=" + teamHome.getName() +
                ", teamAway=" + teamAway.getName() +
                ", seasonGroup=" + seasonGroup.getGroupKey() +
                ", scoreHome=" + scoreHome +
                ", scoreAway=" + scoreAway +
                ", matchDate=" + matchDate +
                ", round=" + round +
                '}';
    }

    public MatchesPayloadDTO toDTO() {
        return new MatchesPayloadDTO(this);
    }
}
