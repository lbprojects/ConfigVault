package com.app.repositories;

import com.app.entites.Environment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

    Environment findByName(String name);

}
