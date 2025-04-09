package com.Protronserver.Protronserver.Controller;

import com.Protronserver.Protronserver.Entities.User;
import com.Protronserver.Protronserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ManageUserController {

    @Autowired
    private UserRepository userRepository;

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
