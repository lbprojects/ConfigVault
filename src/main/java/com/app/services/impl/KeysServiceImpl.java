package com.app.services.impl;

import com.app.entites.Key;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.KeysRepository;
import com.app.services.CommonDataService;
import com.app.services.KeysService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional
@Service
public class KeysServiceImpl implements KeysService {

    private final KeysRepository keysRepository;
    private final CommonDataService commonDataService;

    public KeysServiceImpl(final KeysRepository keysRepository, final CommonDataService commonDataService) {
        this.keysRepository = keysRepository;
        this.commonDataService = commonDataService;
    }

    @Override
    public Key createKey(Key key) {
        commonDataService.isEnvironmentExists(key.getEnvironment());
        commonDataService.isUserExists(key.getUser());
        return keysRepository.save(key);
    }

    @Override
    public Key updateKey(Long id, Key key) {
        Key savedKey = keysRepository.findById(id)
                .orElseThrow(() -> new APIException("Key doesn't exists for update"));
        commonDataService.isEnvironmentExists(key.getEnvironment());
        commonDataService.isUserExists(key.getUser());
        savedKey.setName(key.getName());
        savedKey.setDescription(key.getDescription());
        savedKey.setValue(key.getValue());
        savedKey.setCreator(key.getCreator());
        savedKey.setJira(key.getJira());
        savedKey.setUser(key.getUser());
        savedKey.setEnvironment(key.getEnvironment());
        savedKey.setLastUpdated(new Date());
        log.info(String.format("Saving key with key name: %s and key value: %s", key.getName(), key.getValue()));
        return keysRepository.save(savedKey);
    }

    @Override
    public Key getKey(Long id) {
        return keysRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    @Override
    public void deleteKey(Long id) {
        keysRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key doesn't exists for delete"));
        keysRepository.deleteById(id);
    }

    @Override
    public List<Key> getAllKeys() {
        return keysRepository.findAll();
    }

    @Override
    public List<Key> getAllKeyByEnvironment(String name) {
        return keysRepository.findAllByEnvironment(name);
    }

    @Override
    public List<Key> getAllByKeyName(String name) {
        return keysRepository.findAllByName(name);
    }

}
