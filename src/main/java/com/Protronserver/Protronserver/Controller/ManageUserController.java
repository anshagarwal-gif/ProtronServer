package com.Protronserver.Protronserver.Controller;

import com.Protronserver.Protronserver.DTOs.LoginRequest;
import com.Protronserver.Protronserver.DTOs.UserSignUpDTO;
import com.Protronserver.Protronserver.Entities.User;
import com.Protronserver.Protronserver.Repository.UserRepository;
import com.Protronserver.Protronserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ManageUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody UserSignUpDTO userSignUpDTO) {
        try {
            User createdUser = userService.signupUser(userSignUpDTO);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @GetMapping
    private List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/firstname/{firstName}")
    public List<User> getUsersByFirstName(@PathVariable String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    // Fetch user by email
    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

    // Fetch user by empCode
    @GetMapping("/empcode/{empCode}")
    public Optional<User> getUserByEmpCode(@PathVariable String empCode) {
        return userRepository.findByEmpCode(empCode);
    }

}
