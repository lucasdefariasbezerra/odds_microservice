package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Sport;

import java.util.Objects;

public class SportDTO {
    private Integer id;
    private String name;

    public SportDTO() {
    }

    public SportDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SportDTO(Sport sport) {
        this.id = sport.getId();
        this.name = sport.getName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportDTO sportDTO = (SportDTO) o;
        return Objects.equals(id, sportDTO.id) &&
                Objects.equals(name, sportDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SportDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
