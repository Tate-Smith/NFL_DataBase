package com.Tate.NFL_db.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;
import java.time.Year;

@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Year season;
    @Column()
    @Min(0)
    private int gamesPlayed;
    @Column()
    private int passingAttempts;
    @Column()
    private int completions;
    @Column()
    private int passingYards;
    @Column()
    private int passingTouchdowns;
    @Column()
    private int interceptions;
    @Column()
    private double completionPercentage;
    @Column()
    private double qbr;
    @Column()
    private int rushingYards;
    @Column()
    private int rushingAttempts;
    @Column()
    private int rushingTouchdowns;
    @Column()
    private double yardsPerRush;
    @Column()
    private int receptions;
    @Column()
    private int targets;
    @Column()
    private int receivingYards;
    @Column()
    private int receivingTouchdowns;
    @Column()
    private int totalTackles;
    @Column()
    private int soloTackles;
    @Column()
    private int tacklesForLoss;
    @Column()
    private double sacks;
    @Column()
    private int hurries;
    @Column()
    private int quarterbackHits;
    @Column()
    private int passDeflections;
    @Column()
    private int forcedFumbles;
    @Column()
    private int fumbleRecoveries;
    @Column()
    private int fieldGoalAttempts;
    @Column()
    private int fieldGoalsMade;
    @Column()
    private double puntAverage;
    @Column()
    private double kickoffReturnAverage;
    @Column()
    private double puntReturnAverage;
    @Column()
    private int returnTouchdowns;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Stats() {
    }

    public Stats(Year season, int gamesPlayed, int passingAttempts, int completions, int passingYards, int passingTouchdowns,
                 int interceptions, double completionPercentage, double qbr, int rushingYards, int rushingAttempts, int rushingTouchdowns,
                 int yardsPerRush, int receptions, int targets, int receivingYards, int receivingTouchdowns, int totalTackles,
                 int soloTackles, int tacklesForLoss, double sacks, int hurries, int quarterbackHits, int passDeflections, int forcedFumbles,
                 int fumbleRecoveries, int fieldGoalAttempts, int fieldGoalsMade, double puntAverage, double kickoffReturnAverage,
                 double puntReturnAverage, int returnTouchdowns, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.season = season;
        this.gamesPlayed = gamesPlayed;
        this.passingAttempts = passingAttempts;
        this.completions = completions;
        this.passingYards = passingYards;
        this.passingTouchdowns = passingTouchdowns;
        this.interceptions = interceptions;
        this.completionPercentage = completionPercentage;
        this.qbr = qbr;
        this.rushingYards = rushingYards;
        this.rushingAttempts = rushingAttempts;
        this.rushingTouchdowns = rushingTouchdowns;
        this.yardsPerRush = yardsPerRush;
        this.receptions = receptions;
        this.targets = targets;
        this.receivingYards = receivingYards;
        this.receivingTouchdowns = receivingTouchdowns;
        this.totalTackles = totalTackles;
        this.soloTackles = soloTackles;
        this.tacklesForLoss = tacklesForLoss;
        this.sacks = sacks;
        this.hurries = hurries;
        this.quarterbackHits = quarterbackHits;
        this.passDeflections = passDeflections;
        this.forcedFumbles = forcedFumbles;
        this.fumbleRecoveries = fumbleRecoveries;
        this.fieldGoalAttempts = fieldGoalAttempts;
        this.fieldGoalsMade = fieldGoalsMade;
        this.puntAverage = puntAverage;
        this.kickoffReturnAverage = kickoffReturnAverage;
        this.puntReturnAverage = puntReturnAverage;
        this.returnTouchdowns = returnTouchdowns;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
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

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
