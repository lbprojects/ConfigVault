package com.app.controllers;

import com.app.entites.Key;
import com.app.payloads.APIResponse;
import com.app.services.KeysService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keys")
public class KeysController {

    private final KeysService keysService;

    public KeysController(final KeysService keysService) {
        this.keysService = keysService;
    }

    @PostMapping
    public ResponseEntity<Key> createKey(@Valid @RequestBody Key key) {
        Key savedKey = keysService.createKey(key);
        return new ResponseEntity<>(savedKey, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Key> updateKey(@PathVariable Long id, @Valid @RequestBody Key key) {
        Key updatedKey = keysService.updateKey(id, key);
        return new ResponseEntity<>(updatedKey, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Key> getKey(@PathVariable Long id) {
        Key key = keysService.getKey(id);
        return new ResponseEntity<>(key, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteKey(@PathVariable Long id) {
        keysService.deleteKey(id);
        APIResponse res = new APIResponse("Key deleted successfully", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Key>> getAllKeys() {
        List<Key> keys = keysService.getAllKeys();
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

    @GetMapping("/environment/{name}")
    public ResponseEntity<List<Key>> getAllKeyByEnvironment(@PathVariable String name) {
        List<Key> keys = keysService.getAllKeyByEnvironment(name);
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

    @GetMapping("/key/{name}")
    public ResponseEntity<List<Key>> getAllByKeyName(@PathVariable String name) {
        List<Key> keys = keysService.getAllByKeyName(name);
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

}
