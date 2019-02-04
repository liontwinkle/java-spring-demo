package com.codesample.demo.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class IncidentFilterDto {
    private String titleLike;
    private String sourceUrlLike;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime incidentDateStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime incidentDateEnd;

    public String getTitleLike() {
        return titleLike;
    }

    public void setTitleLike(String titleLike) {
        this.titleLike = titleLike;
    }

    public String getSourceUrlLike() {
        return sourceUrlLike;
    }

    public void setSourceUrlLike(String sourceUrlLike) {
        this.sourceUrlLike = sourceUrlLike;
    }

    public LocalDateTime getIncidentDateStart() {
        return incidentDateStart;
    }

    public void setIncidentDateStart(LocalDateTime incidentDateStart) {
        this.incidentDateStart = incidentDateStart;
    }

    public LocalDateTime getIncidentDateEnd() {
        return incidentDateEnd;
    }

    public void setIncidentDateEnd(LocalDateTime incidentDateEnd) {
        this.incidentDateEnd = incidentDateEnd;
    }
}
