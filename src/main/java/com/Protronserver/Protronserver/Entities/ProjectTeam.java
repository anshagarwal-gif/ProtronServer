package com.Protronserver.Protronserver.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
