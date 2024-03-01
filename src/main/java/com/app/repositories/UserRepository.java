package com.app.repositories;

import com.app.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
    User findByName(String name);
}
