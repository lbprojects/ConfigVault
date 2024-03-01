package com.app.services.impl;

import com.app.entites.Property;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.PropertiesRepository;
import com.app.services.CommonDataService;
import com.app.services.PropertiesService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional
@Service
public class PropertiesServiceImpl implements PropertiesService {

    private final PropertiesRepository propertiesRepository;
    private final CommonDataService commonDataService;

    public PropertiesServiceImpl(final PropertiesRepository propertiesRepository, final CommonDataService commonDataService) {
        this.propertiesRepository = propertiesRepository;
        this.commonDataService = commonDataService;
    }

    @Override
    public Property createProperty(Property property) {
        commonDataService.isEnvironmentExists(property.getEnvironment());
        commonDataService.isUserExists(property.getUser());
        return propertiesRepository.save(property);
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        Property savedProperty = propertiesRepository.findById(id)
                .orElseThrow(() -> new APIException("Property doesn't exists for update"));
        commonDataService.isEnvironmentExists(property.getEnvironment());
        commonDataService.isUserExists(property.getUser());
        savedProperty.setName(property.getName());
        savedProperty.setDescription(property.getDescription());
        savedProperty.setValue(property.getValue());
        savedProperty.setCreator(property.getCreator());
        savedProperty.setJira(property.getJira());
        savedProperty.setUser(property.getUser());
        savedProperty.setEnvironment(property.getEnvironment());
        savedProperty.setLastUpdated(new Date());
        log.info(String.format("Saving Property with Property name: %s and Property value: %s", property.getName(), property.getValue()));
        return propertiesRepository.save(savedProperty);
    }

    @Override
    public Property getProperty(Long id) {
        return propertiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
    }

    @Override
    public void deleteProperty(Long id) {
        propertiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property doesn't exists for delete"));
        propertiesRepository.deleteById(id);
    }

    @Override
    public List<Property> getProperties() {
        return propertiesRepository.findAll();
    }

    @Override
    public List<Property> getAllPropertyByEnvironment(String name) {
        return propertiesRepository.findAllByEnvironment(name);
    }

    @Override
    public List<Property> getAllByPropertyName(String name) {
        return propertiesRepository.findAllByName(name);
    }

}
