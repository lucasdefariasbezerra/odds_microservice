package com.lucasbezerra.oddsproject.model.dto;

import java.util.Objects;

public class TeamDTO {
    private Integer id;
    private String name;
    private SportDTO sport;

    public TeamDTO() {
    }

    public TeamDTO(Integer id, String name, SportDTO sport) {
        this.id = id;
        this.name = name;
        this.sport = sport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportDTO getSport() {
        return sport;
    }

    public void setSport(SportDTO sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equals(id, teamDTO.id) &&
                Objects.equals(name, teamDTO.name) &&
                Objects.equals(sport, teamDTO.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sport);
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport=" + sport +
                '}';
    }
}
