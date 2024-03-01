package com.app.controllers;

import com.app.entites.Environment;
import com.app.payloads.APIResponse;
import com.app.services.EnvironmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/environments")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(final EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping
    public ResponseEntity<Environment> createEnvironment(@Valid @RequestBody Environment environment) {
        Environment savedEnvironment = environmentService.createEnvironment(environment);
        return new ResponseEntity<>(savedEnvironment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Environment> updateEnvironment(@PathVariable Long id, @Valid @RequestBody Environment environment) {
        Environment updatedEnvironment = environmentService.updateEnvironment(id, environment);
        return new ResponseEntity<>(updatedEnvironment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Environment> getEnvironment(@PathVariable Long id) {
        Environment environment = environmentService.getEnvironment(id);
        return new ResponseEntity<>(environment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteEnvironment(@PathVariable Long id) {
        environmentService.deleteEnvironment(id);
        APIResponse res = new APIResponse("Environment deleted successfully", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Environment>> getAllEnvironments() {
        List<Environment> environment = environmentService.getAllEnvironments();
        return new ResponseEntity<>(environment, HttpStatus.OK);
    }

}
