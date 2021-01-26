package com.lucasbezerra.oddsproject.model;

import com.lucasbezerra.oddsproject.model.dto.SeasonDTO;
import com.lucasbezerra.oddsproject.model.dto.SeasonRequestDTO;

import javax.persistence.*;

@Entity
@Table(name="season")
public class Season {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    public Season() {}

    public Season(SeasonRequestDTO seasonPayload) {
        Tournment tournment = new Tournment();
        this.setNameRight(seasonPayload.getNameRight());
        this.setTournmentType(seasonPayload.getType());
        this.setSeasonStart(seasonPayload.getSeasonStart());
        this.setSeasonEnd(seasonPayload.getSeasonEnd());
        tournment.setId(seasonPayload.getTournment().getId());
        this.setTournment(tournment);
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

    public SeasonDTO toDTO() {
        return new SeasonDTO(this);
    }
}
