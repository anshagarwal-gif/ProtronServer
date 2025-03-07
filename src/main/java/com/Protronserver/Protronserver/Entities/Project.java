package com.Protronserver.Protronserver.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;
    private String projectIcon;
    private Date startDate;
    private Date endDate;
    private Double projectCost;

    @ManyToOne
    @JoinColumn(name = "project_manager_id")
    private User projectManager;

    @OneToMany(mappedBy = "project")
    private List<TimesheetTask> timesheetTasks;
}