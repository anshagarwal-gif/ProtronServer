package com.Protronserver.Protronserver.Service;

import com.Protronserver.Protronserver.DTOs.ProjectRequestDTO;
import com.Protronserver.Protronserver.Entities.Project;
import com.Protronserver.Protronserver.Entities.User;
import com.Protronserver.Protronserver.Repository.ProjectRepository;
import com.Protronserver.Protronserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project addProject(ProjectRequestDTO request) {
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setProjectIcon(request.getProjectIcon());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setProjectCost(request.getProjectCost());

        // fetch the actual User object from the DB
        System.out.println(request.getProjectManagerId());
        User manager = userRepository.findById(request.getProjectManagerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        project.setProjectManager(manager);

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
    }

}
