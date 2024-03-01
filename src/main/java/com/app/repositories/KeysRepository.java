package com.app.repositories;

import com.app.entites.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface KeysRepository extends JpaRepository<Key, Long> {

    List<Key> findAllByEnvironment(String postCode);
    List<Key> findAllByName(String name);

    @Modifying
    @Query("Update Key key set key.user = ?1 where key.user = ?2")
    void updateUserByName(String userName, String savedUserName);

    @Modifying
    @Query("Update Key key set key.environment = ?1 where key.environment = ?2")
    void updateEnvironmentByName(String name, String savedName);

    @Modifying
    @Query("Update Key key set key.environment = null where key.environment = ?1")
    void deleteEnvironment(String name);

}
