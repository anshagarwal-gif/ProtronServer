package com.Protronserver.Protronserver.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeamMemberRequestDTO {
    private Double pricing;
    private String empCode;
    private String status;
    private Long projectId;
    private String taskType;
    private String unit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate estimatedReleaseDate;

    public LocalDate getEstimatedReleaseDate() {
        return estimatedReleaseDate;
    }

    public void setEstimatedReleaseDate(LocalDate estimatedReleaseDate) {
        this.estimatedReleaseDate = estimatedReleaseDate;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPricing() {
        return pricing;
    }

    public void setPricing(Double pricing) {
        this.pricing = pricing;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long userId;
}
