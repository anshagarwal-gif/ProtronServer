package com.Protronserver.Protronserver.Service;

import com.Protronserver.Protronserver.DTOs.ProjectRequestDTO;
import com.Protronserver.Protronserver.DTOs.TeamMemberRequestDTO;
import com.Protronserver.Protronserver.Entities.Project;
import com.Protronserver.Protronserver.Entities.ProjectTeam;
import com.Protronserver.Protronserver.Entities.User;
import com.Protronserver.Protronserver.Repository.ProjectRepository;
import com.Protronserver.Protronserver.Repository.ProjectTeamRepository;
import com.Protronserver.Protronserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectTeamRepository projectTeamRepository;

    public Project addProject(ProjectRequestDTO request) {
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setProjectIcon(request.getProjectIcon());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setProjectCost(request.getProjectCost());
        project.setTenent(request.getTenent());

        // fetch the actual User object from the DB
        System.out.println(request.getProjectManagerId());
        User manager = userRepository.findById(request.getProjectManagerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        project.setProjectManager(manager);
        Project savedProject = projectRepository.save(project);

        // If project team members are provided, save them
        if (request.getProjectTeam() != null && !request.getProjectTeam().isEmpty()) {
            List<ProjectTeam> teamMembers = new ArrayList<>();

            for (TeamMemberRequestDTO memberDTO : request.getProjectTeam()) {
                User user = userRepository.findById(memberDTO.getUserId())
                        .orElseThrow(() -> new RuntimeException("Team member not found"));

                ProjectTeam teamMember = new ProjectTeam();
                teamMember.setProject(savedProject);
                teamMember.setUser(user);
                teamMember.setPricing(memberDTO.getPricing());
                teamMember.setEmpCode(user.getEmpCode());
                teamMember.setStatus(memberDTO.getStatus() != null ? memberDTO.getStatus() : "active");
                teamMember.setTaskType(memberDTO.getTaskType());
                teamMember.setUnit(memberDTO.getUnit());
                teamMember.setEstimatedReleaseDate(memberDTO.getEstimatedReleaseDate());

                teamMembers.add(projectTeamRepository.save(teamMember));
            }

            // Update project with team members
            savedProject.setProjectTeam(teamMembers);
        }
        return savedProject;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
    }

}
