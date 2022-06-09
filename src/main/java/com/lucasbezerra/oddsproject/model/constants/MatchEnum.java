package com.lucasbezerra.oddsproject.model.constants;

public enum MatchEnum {
    ID(0),
    SEASON_ID(1),
    DESCRIPTION(2),
    GROUP_KEY(3),
    TEAM_HOME(4),
    TEAM_AWAY(5),
    SCORE_HOME(6),
    SCORE_AWAY(7),
    ROUND(8),
    MATCH_DATE(9),
    PROCESSED(10);

    private final int value;

    public int getValue() {
        return value;
    }

    private MatchEnum(int value) {
       this.value = value;
    }

}
