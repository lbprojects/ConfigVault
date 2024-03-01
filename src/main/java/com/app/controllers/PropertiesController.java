package com.app.controllers;

import com.app.entites.Property;
import com.app.payloads.APIResponse;
import com.app.services.PropertiesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    private final PropertiesService propertiesService;

    public PropertiesController(final PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@Valid @RequestBody Property property) {
        Property savedProperty = propertiesService.createProperty(property);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @Valid @RequestBody Property property) {
        Property updatedProperty = propertiesService.updateProperty(id, property);
        return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {
        Property property = propertiesService.getProperty(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteProperty(@PathVariable Long id) {
        propertiesService.deleteProperty(id);
        APIResponse res = new APIResponse("Property deleted successfully", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllPropertys() {
        List<Property> properties = propertiesService.getProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/environment/{name}")
    public ResponseEntity<List<Property>> getAllPropertyByEnvironment(@PathVariable String name) {
        List<Property> properties = propertiesService.getAllPropertyByEnvironment(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/property/{name}")
    public ResponseEntity<List<Property>> getAllByPropertyName(@PathVariable String name) {
        List<Property> properties = propertiesService.getAllByPropertyName(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

}
