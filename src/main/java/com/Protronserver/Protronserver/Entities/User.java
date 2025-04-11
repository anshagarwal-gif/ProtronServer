package com.Protronserver.Protronserver.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String empCode;

    private String firstName;
    private String lastName;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    @Column(unique = true)
    private String email;

    private String password;
    private String status;
    private Date lastLogin;
    private String profilePhoto;
    private Date dateOfJoining;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<TimesheetTask> timesheetTasks;

    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "projectManager")
    @JsonIgnoreProperties("projectManager")
    private List<Project> projectsManaged; // Projects managed by the user

    public List<Project> getProjectsManaged() {
        return projectsManaged;
    }

    public void setProjectsManaged(List<Project> projectsManaged) {
        this.projectsManaged = projectsManaged;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<ProjectTeam> projectTeams; // Projects user is part of as a team member

    public List<ProjectTeam> getProjectTeams() {
        return projectTeams;
    }

    public void setProjectTeams(List<ProjectTeam> projectTeams) {
        this.projectTeams = projectTeams;
    }

    @PrePersist
    public void generateEmpCode() {
        if (this.empCode == null || this.empCode.isEmpty()) {
            this.empCode = "EMP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<TimesheetTask> getTimesheetTasks() {
        return timesheetTasks;
    }

    public void setTimesheetTasks(List<TimesheetTask> timesheetTasks) {
        this.timesheetTasks = timesheetTasks;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

//    @Transient
//    @JsonProperty("projects") // this will add the field to the JSON response
//    @JsonManagedReference
//    public List<Project> getAllProjects() {
//        Set<Project> allProjects = new HashSet<>();
//
//        if (this.projects != null) {
//            allProjects.addAll(this.projects); // As manager
//        }
//
//        if (this.projectTeams != null) {
//            for (ProjectTeam pt : this.projectTeams) {
//                if (pt.getProject() != null) {
//                    allProjects.add(pt.getProject()); // As team member
//                }
//            }
//        }
//
//        return new ArrayList<>(allProjects);
//    }
}
