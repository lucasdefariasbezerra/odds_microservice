package com.lucasbezerra.oddsproject.model.dto;

import java.util.Objects;

public class CountryDTO {
    private int id;
    private String name;
    private String region;
    private String threeLetterCode;

    public CountryDTO() {
    }

    public CountryDTO(int id, String name, String region, String threeLetterCode) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.threeLetterCode = threeLetterCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getThreeLetterCode() {
        return threeLetterCode;
    }

    public void setThreeLetterCode(String threeLetterCode) {
        this.threeLetterCode = threeLetterCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDTO that = (CountryDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(region, that.region) &&
                Objects.equals(threeLetterCode, that.threeLetterCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region, threeLetterCode);
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", threeLetterCode='" + threeLetterCode + '\'' +
                '}';
    }
}
