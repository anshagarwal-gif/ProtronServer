package com.Protronserver.Protronserver.Entities;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projectTeamId")
@Getter
@Setter
@Entity
@Table(name = "project_team")
public class ProjectTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectTeamId;
    private Double pricing;
    private String empCode;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties({"team", "projectManager"})
    private Project project;

    public Long getProjectTeamId() {
        return projectTeamId;
    }

    public Double getPricing() {
        return pricing;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getStatus() {
        return status;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"projectTeams", "projectsManaged"})
    private User user;

    public void setProjectTeamId(Long projectTeamId) {
        this.projectTeamId = projectTeamId;
    }

    public void setPricing(Double pricing) {
        this.pricing = pricing;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
