package com.lucasbezerra.oddsproject.model;

import javax.persistence.*;

@Entity
@Table(name="season")
public class Season {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_right")
    private String nameRight;

    @Column(name = "tournment_type")
    private String tournmentType;

    @Column(name = "season_start")
    private Long seasonStart;

    @Column(name = "season_end")
    private Long seasonEnd;

    @ManyToOne
    @JoinColumn(name="tournment_id", nullable = false)
    private Tournment tournment;

    public Season() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRight() {
        return nameRight;
    }

    public void setNameRight(String nameRight) {
        this.nameRight = nameRight;
    }

    public String getTournmentType() {
        return tournmentType;
    }

    public void setTournmentType(String tournmentType) {
        this.tournmentType = tournmentType;
    }

    public Long getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(Long seasonStart) {
        this.seasonStart = seasonStart;
    }

    public Long getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(Long seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public Tournment getTournment() {
        return tournment;
    }

    public void setTournment(Tournment tournment) {
        this.tournment = tournment;
    }
}
