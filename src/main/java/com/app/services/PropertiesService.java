package com.app.services;



import com.app.entites.Property;

import java.util.List;

public interface PropertiesService {

    Property createProperty(Property property);
    Property updateProperty(Long id, Property property);
    Property getProperty(Long id);
    void deleteProperty(Long id);
    List<Property> getProperties();
    List<Property> getAllPropertyByEnvironment(String name);
    List<Property> getAllByPropertyName(String name);
}
