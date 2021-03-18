package com.example.autogarage.repository;

import com.example.autogarage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Collection<User> findAllByUsername(String username);


}
