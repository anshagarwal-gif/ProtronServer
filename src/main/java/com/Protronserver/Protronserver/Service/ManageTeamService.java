package com.Protronserver.Protronserver.Service;

import com.Protronserver.Protronserver.DTOs.TeamMemberEditDTO;
import com.Protronserver.Protronserver.DTOs.TeamMemberRequestDTO;
import com.Protronserver.Protronserver.Entities.Project;
import com.Protronserver.Protronserver.Entities.ProjectTeam;
import com.Protronserver.Protronserver.Entities.User;
import com.Protronserver.Protronserver.Repository.ProjectRepository;
import com.Protronserver.Protronserver.Repository.ProjectTeamRepository;
import com.Protronserver.Protronserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageTeamService {

    @Autowired
    private ProjectTeamRepository projectTeamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectTeam createProjectTeam(TeamMemberRequestDTO dto) {
        ProjectTeam team = new ProjectTeam();
        team.setPricing(dto.getPricing());
        team.setEmpCode(dto.getEmpCode());
        team.setStatus(dto.getStatus());
        team.setUnit(dto.getUnit());
        team.setTaskType(dto.getTaskType());
        team.setEstimatedReleaseDate(dto.getEstimatedReleaseDate());

        Optional<Project> project = projectRepository.findById(dto.getProjectId());
        Optional<User> user = userRepository.findById(dto.getUserId());

        if (project.isPresent() && user.isPresent()) {
            team.setProject(project.get());
            team.setUser(user.get());
            return projectTeamRepository.save(team);
        }

        throw new RuntimeException("Project or User not found");
    }

    public ProjectTeam getProjectTeamById(Long id) {
        return projectTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team Member not found"));
    }

    public ProjectTeam updateProjectTeam(Long id, TeamMemberEditDTO dto) {
        ProjectTeam team = getProjectTeamById(id);
        team.setPricing(dto.getPricing());
        team.setUnit(dto.getUnit());
        team.setEstimatedReleaseDate(dto.getEstimatedReleaseDate());

        return projectTeamRepository.save(team);
    }

    public ProjectTeam updateStatus(Long teamMemberId, String status) {
        ProjectTeam team = getProjectTeamById(teamMemberId);
        team.setStatus(status);
        return projectTeamRepository.save(team);
    }

    public void deleteProjectTeam(Long id) {
        ProjectTeam team = getProjectTeamById(id);
        projectTeamRepository.delete(team);
    }

    public List<ProjectTeam> getProjectTeam(Long projectId) {
        return projectTeamRepository.findByProjectProjectId(projectId);
    }

}
