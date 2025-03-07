package com.Protronserver.Protronserver.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;

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
}
