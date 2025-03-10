package com.Protronserver.Protronserver.Controller;

import com.Protronserver.Protronserver.Entities.ProjectTeam;
import com.Protronserver.Protronserver.Service.ManageTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project-team")
public class ManageTeamController {

    @Autowired
    private ManageTeamService manageTeamService;

    @PostMapping("/add")
    public ResponseEntity<ProjectTeam> addTeamMember(@RequestBody Map<String, Object> requestData) {
        Double pricing = Double.valueOf(requestData.get("pricing").toString());
        String empCode = requestData.get("empCode").toString();
        String status = requestData.get("status").toString();
        Long projectId = Long.valueOf(requestData.get("projectId").toString());
        Long userId = Long.valueOf(requestData.get("userId").toString());

        ProjectTeam projectTeam = new ProjectTeam();
        projectTeam.setPricing(pricing);
        projectTeam.setEmpCode(empCode);
        projectTeam.setStatus(status);

        ProjectTeam savedTeamMember = manageTeamService.addTeamMember(projectTeam, projectId, userId);
        return ResponseEntity.ok(savedTeamMember);
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectTeam> editTeamMember(@PathVariable Long id, @RequestBody ProjectTeam projectTeam) {
        System.out.println(id);
        System.out.println(projectTeam.getPricing());
        return ResponseEntity.ok(manageTeamService.editTeamMember(id, projectTeam));
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<ProjectTeam> changeMemberStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(manageTeamService.changeMemberStatus(id, status));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTeamMember(@PathVariable Long id) {
        manageTeamService.removeTeamMember(id);
        return ResponseEntity.ok("Member removed successfully");
    }

    @GetMapping("/list/{projectId}")
    public ResponseEntity<List<ProjectTeam>> getProjectTeam(@PathVariable Long projectId) {
        return ResponseEntity.ok(manageTeamService.getProjectTeam(projectId));
    }

}
