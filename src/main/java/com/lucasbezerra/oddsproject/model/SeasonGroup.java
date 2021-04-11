package com.lucasbezerra.oddsproject.model;

import javax.persistence.*;

@Entity
@Table(name="season_group")
public class SeasonGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String desc;

    @Column(name = "group_key")
    private String groupKey;

    @ManyToOne
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }
}
