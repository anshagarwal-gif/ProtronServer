package com.Protronserver.Protronserver.Service;

import com.Protronserver.Protronserver.Entities.Project;
import com.Protronserver.Protronserver.Entities.ProjectTeam;
import com.Protronserver.Protronserver.Entities.User;
import com.Protronserver.Protronserver.Repository.ProjectRepository;
import com.Protronserver.Protronserver.Repository.ProjectTeamRepository;
import com.Protronserver.Protronserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageTeamService {

    @Autowired
    private ProjectTeamRepository projectTeamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectTeam addTeamMember(ProjectTeam projectTeam, Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        projectTeam.setProject(project);
        projectTeam.setUser(user);

        return projectTeamRepository.save(projectTeam);
    }

    public ProjectTeam editTeamMember(Long id, ProjectTeam updatedProjectTeam) {
        return projectTeamRepository.findById(id).map(teamMember -> {
            teamMember.setPricing(updatedProjectTeam.getPricing());
            teamMember.setEmpCode(updatedProjectTeam.getEmpCode());
            teamMember.setStatus(updatedProjectTeam.getStatus());
            return projectTeamRepository.save(teamMember);
        }).orElseThrow(() -> new RuntimeException("Team member not found"));
    }

    public ProjectTeam changeMemberStatus(Long id, String status) {
        return projectTeamRepository.findById(id).map(teamMember -> {
            teamMember.setStatus(status);
            return projectTeamRepository.save(teamMember);
        }).orElseThrow(() -> new RuntimeException("Team member not found"));
    }

    public void removeTeamMember(Long id) {
        projectTeamRepository.deleteById(id);
    }

    public List<ProjectTeam> getProjectTeam(Long projectId) {
        return projectTeamRepository.findByProjectProjectId(projectId);
    }

}
