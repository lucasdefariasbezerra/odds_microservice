package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Match;
import com.lucasbezerra.oddsproject.model.SeasonGroup;
import com.lucasbezerra.oddsproject.model.constants.MatchEnum;
import com.lucasbezerra.oddsproject.utils.DateUtils;

public class MatchesPayloadDTO {
    private int id;
    private int seasonId;
    private String groupDesc;
    private String groupKey;
    private String teamHome;
    private String teamAway;
    private int scoreHome;
    private int scoreAway;
    private int round;
    private String date;
    private int processed;

    public MatchesPayloadDTO() {
    }

    public MatchesPayloadDTO(int seasonId, String groupDesc, String groupKey, String teamHome,
                             String teamAway, int scoreHome, int scoreAway, int round, String date) {
        this.seasonId = seasonId;
        this.groupDesc = groupDesc;
        this.groupKey = groupKey;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.round = round;
        this.date = date;
    }

    public MatchesPayloadDTO (Match match) {
        SeasonGroup seasonGroup = match.getSeasonGroup();
        this.id = match.getId();
        this.seasonId = seasonGroup.getSeason().getId();
        this.groupDesc = seasonGroup.getDesc();
        this.groupKey = seasonGroup.getGroupKey();
        this.teamHome = match.getTeamHome().getName();
        this.teamAway = match.getTeamAway().getName();
        this.scoreHome = match.getScoreHome();
        this.scoreAway = match.getScoreAway();
        this.round = match.getRound();
        this.date = DateUtils.convertDateToString(match.getMatchDate(), "dd/MM/yyyy");
    }

    public MatchesPayloadDTO (Object[] matchRow) {
        this.id = (int) matchRow[MatchEnum.ID.getValue()];
        this.seasonId = (int) matchRow[MatchEnum.SEASON_ID.getValue()];
        this.groupDesc = (String) matchRow[MatchEnum.DESCRIPTION.getValue()];
        this.groupKey = (String) matchRow[MatchEnum.GROUP_KEY.getValue()];
        this.teamHome = (String) matchRow[MatchEnum.TEAM_HOME.getValue()];
        this.teamAway = (String) matchRow[MatchEnum.TEAM_AWAY.getValue()];
        this.scoreHome = (int) matchRow[MatchEnum.SCORE_HOME.getValue()];
        this.scoreAway = (int) matchRow[MatchEnum.SCORE_AWAY.getValue()];
        this.round = (int) matchRow[MatchEnum.ROUND.getValue()];
        this.date = DateUtils.convertDateToString(Long.parseLong(String.valueOf(matchRow[MatchEnum.MATCH_DATE.getValue()])), "dd/MM/yyyy");
        this.processed = (int) matchRow[MatchEnum.PROCESSED.getValue()];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }
}
