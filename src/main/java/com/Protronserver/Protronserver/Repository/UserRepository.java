package com.Protronserver.Protronserver.Repository;

import com.Protronserver.Protronserver.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameIgnoreCase(String firstName);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmpCode(String empCode);
    boolean existsByEmail(String email);

}
