package com.lucasbezerra.oddsproject.model.dto;

import java.util.Objects;

public class TeamDTO {
    private Integer id;
    private String name;
    private SportDTO sport;
    private CountryDTO country;

    public TeamDTO() {
    }

    public TeamDTO(Integer id, String name, SportDTO sport, CountryDTO country) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.country = country;
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.country = countryDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equals(id, teamDTO.id) &&
                Objects.equals(name, teamDTO.name) &&
                Objects.equals(sport, teamDTO.sport) &&
                Objects.equals(country, teamDTO.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sport, country);
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport=" + sport +
                ", country=" + country +
                '}';
    }
}
