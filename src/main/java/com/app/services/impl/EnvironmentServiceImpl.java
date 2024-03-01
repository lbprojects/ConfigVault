package com.app.services.impl;

import com.app.entites.Environment;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.EnvironmentRepository;
import com.app.services.CommonDataService;
import com.app.services.EnvironmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Transactional
@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;
    private final CommonDataService commonDataService;

    public EnvironmentServiceImpl(final EnvironmentRepository environmentRepository, final CommonDataService commonDataService) {
        this.environmentRepository = environmentRepository;
        this.commonDataService = commonDataService;
    }

    @Override
    public Environment createEnvironment(Environment environment) {
        Environment existingEnvironment = environmentRepository.findByName(environment.getName());
        if (existingEnvironment != null)
            throw new APIException("Environment already exists with environment name: " + environment.getName());
        return environmentRepository.save(environment);
    }

    @Override
    public Environment updateEnvironment(Long id, Environment environment) {
        Environment savedEnvironment = environmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Environment is not exists for update"));
        String environmentName = environment.getName();
        if (!savedEnvironment.getName().equals(environmentName)) {
            commonDataService.updateEnvironmentByName(environmentName, savedEnvironment.getName());
            savedEnvironment.setName(environmentName);
        }
        savedEnvironment.setName(environment.getName());
        savedEnvironment.setIp(environment.getIp());
        log.info(String.format("Saving environment with environment name: %s", environment.getName()));
        return environmentRepository.save(savedEnvironment);
    }

    @Override
    public Environment getEnvironment(Long id) {
        return environmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Environment not found"));
    }


    @Override
    public void deleteEnvironment(Long id) {
        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Environment doesn't exists for delete"));
        commonDataService.deleteEnvironment(environment.getName());
        environmentRepository.deleteById(id);
    }

    @Override
    public List<Environment> getAllEnvironments() {
        return environmentRepository.findAll();
    }

}
