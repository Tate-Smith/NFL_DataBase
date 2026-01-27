package com.Tate.NFL_db.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamDTO {
    private int id;
    @NotBlank
    @Size(min = 1, max = 10, message = "ExternalId must be between 1 and 10 inclusive")
    private String externalId;
    @NotBlank
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 inclusive")
    private String name;
    @NotBlank
    @Size(min = 2, max = 20, message = "City must be between 2 and 20 inclusive")
    private String city;
    @NotBlank
    @Size(min = 2, max = 4, message = "Abbreviation must be between 2 and 4 inclusive")
    private String abbreviation;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
