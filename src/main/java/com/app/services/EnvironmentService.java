package com.app.services;


import com.app.entites.Environment;

import java.util.List;

public interface EnvironmentService {

    Environment createEnvironment(Environment environment);
    Environment updateEnvironment(Long id, Environment environment);
    Environment getEnvironment(Long id);
    void deleteEnvironment(Long id);
    List<Environment> getAllEnvironments();
}
