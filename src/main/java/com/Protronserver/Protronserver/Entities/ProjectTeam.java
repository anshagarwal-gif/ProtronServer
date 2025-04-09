package com.Protronserver.Protronserver.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
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
