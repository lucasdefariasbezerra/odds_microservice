package com.lucasbezerra.oddsproject.model.dto;

public class SeasonRequestDTO {
    private Integer id;
    private String nameRight;
    private String type;
    private Long seasonStart;
    private Long seasonEnd;
    private TournmentDTO tournment;

    public SeasonRequestDTO() {}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public TournmentDTO getTournment() {
        return tournment;
    }

    public void setTournment(TournmentDTO tournment) {
        this.tournment = tournment;
    }
}
