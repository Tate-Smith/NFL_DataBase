package com.Tate.NFL_db.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public class StatsDTO {
    private int id;
    @NotNull(message = "Player external Id is required")
    private String  playerExternalId;
    @NotNull
    private Year season;
    @Min(0)
    private int gamesPlayed;
    private int passingAttempts;
    private int completions;
    private int passingYards;
    private int passingTouchdowns;
    private int interceptions;
    private double completionPercentage;
    private double qbr;
    private int rushingYards;
    private int rushingAttempts;
    private int rushingTouchdowns;
    private double yardsPerRush;
    private int receptions;
    private int targets;
    private int receivingYards;
    private int receivingTouchdowns;
    private int totalTackles;
    private int soloTackles;
    private int tacklesForLoss;
    private double sacks;
    private int hurries;
    private int quarterbackHits;
    private int passDeflections;
    private int forcedFumbles;
    private int fumbleRecoveries;
    private int fieldGoalAttempts;
    private int fieldGoalsMade;
    private double puntAverage;
    private double kickoffReturnAverage;
    private double puntReturnAverage;
    private int returnTouchdowns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerExternalId() {
        return playerExternalId;
    }

    public void setPlayerExternalId(String playerExternalId) {
        this.playerExternalId = playerExternalId;
    }

    public Year getSeason() {
        return season;
    }

    public void setSeason(Year season) {
        this.season = season;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getPassingAttempts() {
        return passingAttempts;
    }

    public void setPassingAttempts(int passingAttempts) {
        this.passingAttempts = passingAttempts;
    }

    public int getCompletions() {
        return completions;
    }

    public void setCompletions(int completions) {
        this.completions = completions;
    }

    public int getPassingYards() {
        return passingYards;
    }

    public void setPassingYards(int passingYards) {
        this.passingYards = passingYards;
    }

    public int getPassingTouchdowns() {
        return passingTouchdowns;
    }

    public void setPassingTouchdowns(int passingTouchdowns) {
        this.passingTouchdowns = passingTouchdowns;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public double getQbr() {
        return qbr;
    }

    public void setQbr(double qbr) {
        this.qbr = qbr;
    }

    public int getRushingYards() {
        return rushingYards;
    }

    public void setRushingYards(int rushingYards) {
        this.rushingYards = rushingYards;
    }

    public int getRushingAttempts() {
        return rushingAttempts;
    }

    public void setRushingAttempts(int rushingAttempts) {
        this.rushingAttempts = rushingAttempts;
    }

    public int getRushingTouchdowns() {
        return rushingTouchdowns;
    }

    public void setRushingTouchdowns(int rushingTouchdowns) {
        this.rushingTouchdowns = rushingTouchdowns;
    }

    public double getYardsPerRush() {
        return yardsPerRush;
    }

    public void setYardsPerRush(double yardsPerRush) {
        this.yardsPerRush = yardsPerRush;
    }

    public int getReceptions() {
        return receptions;
    }

    public void setReceptions(int receptions) {
        this.receptions = receptions;
    }

    public int getTargets() {
        return targets;
    }

    public void setTargets(int targets) {
        this.targets = targets;
    }

    public int getReceivingYards() {
        return receivingYards;
    }

    public void setReceivingYards(int receivingYards) {
        this.receivingYards = receivingYards;
    }

    public int getReceivingTouchdowns() {
        return receivingTouchdowns;
    }

    public void setReceivingTouchdowns(int receivingTouchdowns) {
        this.receivingTouchdowns = receivingTouchdowns;
    }

    public int getTotalTackles() {
        return totalTackles;
    }

    public void setTotalTackles(int totalTackles) {
        this.totalTackles = totalTackles;
    }

    public int getSoloTackles() {
        return soloTackles;
    }

    public void setSoloTackles(int soloTackles) {
        this.soloTackles = soloTackles;
    }

    public int getTacklesForLoss() {
        return tacklesForLoss;
    }

    public void setTacklesForLoss(int tacklesForLoss) {
        this.tacklesForLoss = tacklesForLoss;
    }

    public double getSacks() {
        return sacks;
    }

    public void setSacks(double sacks) {
        this.sacks = sacks;
    }

    public int getHurries() {
        return hurries;
    }

    public void setHurries(int hurries) {
        this.hurries = hurries;
    }

    public int getQuarterbackHits() {
        return quarterbackHits;
    }

    public void setQuarterbackHits(int quarterbackHits) {
        this.quarterbackHits = quarterbackHits;
    }

    public int getPassDeflections() {
        return passDeflections;
    }

    public void setPassDeflections(int passDeflections) {
        this.passDeflections = passDeflections;
    }

    public int getForcedFumbles() {
        return forcedFumbles;
    }

    public void setForcedFumbles(int forcedFumbles) {
        this.forcedFumbles = forcedFumbles;
    }

    public int getFumbleRecoveries() {
        return fumbleRecoveries;
    }

    public void setFumbleRecoveries(int fumbleRecoveries) {
        this.fumbleRecoveries = fumbleRecoveries;
    }

    public int getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public void setFieldGoalAttempts(int fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public double getPuntAverage() {
        return puntAverage;
    }

    public void setPuntAverage(double puntAverage) {
        this.puntAverage = puntAverage;
    }

    public double getKickoffReturnAverage() {
        return kickoffReturnAverage;
    }

    public void setKickoffReturnAverage(double kickoffReturnAverage) {
        this.kickoffReturnAverage = kickoffReturnAverage;
    }

    public double getPuntReturnAverage() {
        return puntReturnAverage;
    }

    public void setPuntReturnAverage(double puntReturnAverage) {
        this.puntReturnAverage = puntReturnAverage;
    }

    public int getReturnTouchdowns() {
        return returnTouchdowns;
    }

    public void setReturnTouchdowns(int returnTouchdowns) {
        this.returnTouchdowns = returnTouchdowns;
    }
}
