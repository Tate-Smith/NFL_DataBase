package com.Tate.NFL_db.exception;

import java.time.Instant;
import java.util.List;

public class ApiError {
    private final Instant now = Instant.now();
    private final int statusCode;
    private final String error;
    private final List<String> info;

    public ApiError(int statusCode, String error, List<String> info) {
        this.statusCode = statusCode;
        this.error = error;
        this.info = info;
    }

    public Instant getNow() {
        return now;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public List<String> getInfo() {
        return info;
    }
}
