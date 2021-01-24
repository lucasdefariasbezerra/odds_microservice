package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Season;
import com.lucasbezerra.oddsproject.utils.DateUtils;

import java.util.Date;

public class SeasonDTO {
    private Integer id;
    private String name;
    private String type;
    private String description;
    private String seasonDate;

    public SeasonDTO() {
    }

    public SeasonDTO(Season season) {
        this.id = season.getId();
        this.name = extractName(season);
        this.description = season.getTournment().getDescription();
        this.type = season.getTournmentType();
        this.seasonDate = extractYear(season);
    }

    public String getSeasonDate() {
        return seasonDate;
    }

    public void setSeasonDate(String seasonDate) {
        this.seasonDate = seasonDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String extractYear(Season season) {
        Date seasonStart = DateUtils.getDateFromMilli(season.getSeasonStart());
        Date seasonEnd = DateUtils.getDateFromMilli(season.getSeasonEnd());
        int startYear = DateUtils.getYearFromDate(seasonStart);
        int endYear = DateUtils.getYearFromDate(seasonEnd);
        if (startYear == endYear)
            return String.valueOf(startYear);
        return DateUtils.getYearFromDate(seasonStart) + "/" + DateUtils.getYearFromDate(seasonEnd);
    }

    private String extractName (Season season) {
        return season.getNameRight() + " " + season.getTournment().getName();
    };
}
