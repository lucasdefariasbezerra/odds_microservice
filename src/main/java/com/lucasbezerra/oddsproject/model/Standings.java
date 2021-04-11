package com.lucasbezerra.oddsproject.model;

import javax.persistence.*;

@Entity
@Table(name="match")
public class Standings {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="team_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private SeasonGroup seasonGroup;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "lose")
    private Integer lose;

    @Column(name = "draw")
    private Integer draw;

    public Standings() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public SeasonGroup getSeasonGroup() {
        return seasonGroup;
    }

    public void setSeasonGroup(SeasonGroup seasonGroup) {
        this.seasonGroup = seasonGroup;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }
}
