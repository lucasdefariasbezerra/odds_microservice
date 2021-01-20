package com.lucasbezerra.oddsproject.model.dto;

public class TeamUploadDTO {
    private String name;
    private Integer sportId;
    private Integer countryId;

    public TeamUploadDTO() {
    }

    public TeamUploadDTO(String name, Integer sportId, Integer countryId) {
        this.name = name;
        this.sportId = sportId;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}
