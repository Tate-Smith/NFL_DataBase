package com.Tate.NFL_db.dto;

import com.Tate.NFL_db.Model.Position;
import com.Tate.NFL_db.Model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlayerDTO {
    private int id;
    @NotBlank
    @Size(min = 1, max = 10, message = "ExternalId must be between 1 and 10 inclusive")
    private String externalId;
    @NotBlank
    @Size(min = 2, max = 75, message = "Full Name must be between 2 and 75 inclusive")
    private String fullName;
    @NotNull
    private Position position;
    @NotNull
    private Status status;
    @NotBlank
    @Size(min = 1, max = 2, message = "Number must between 1 and 2 digits")
    private String number;
    @NotBlank
    private String teamName;
    private String teamExternalId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamExternalId() {
        return this.teamExternalId;
    }

    public void setTeamExternalId(String teamExternalId) {
        this.teamExternalId = teamExternalId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}