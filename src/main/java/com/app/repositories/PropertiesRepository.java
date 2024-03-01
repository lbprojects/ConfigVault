package com.app.repositories;

import com.app.entites.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PropertiesRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByEnvironment(String postCode);
    List<Property> findAllByName(String name);

    @Modifying
    @Query("Update Property prop set prop.user = ?1 where prop.user = ?2")
    void updateUserByName(String userName, String savedUserName);

    @Modifying
    @Query("Update Property prop set prop.environment = ?1 where prop.environment = ?2")
    void updateEnvironmentByName(String name, String savedName);

    @Modifying
    @Query("Update Property prop set prop.environment = null where prop.environment = ?1")
    void deleteEnvironment(String name);
}
