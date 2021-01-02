package com.lucasbezerra.oddsproject.model;

import com.lucasbezerra.oddsproject.model.dto.CountryDTO;
import com.lucasbezerra.oddsproject.model.dto.SportDTO;
import com.lucasbezerra.oddsproject.model.dto.TeamDTO;

import javax.persistence.*;

@Entity
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="sport_id", nullable = false)
    private Sport sport;

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)
    private Country country;

    public Team() {
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public TeamDTO toDTO() {
        return new TeamDTO(id, name, new SportDTO(sport), new CountryDTO(country));
    }
}
