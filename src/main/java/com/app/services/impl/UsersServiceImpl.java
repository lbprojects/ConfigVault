package com.app.services.impl;

import com.app.entites.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.UserRepository;
import com.app.services.CommonDataService;
import com.app.services.UsersService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Transactional
@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final CommonDataService commonDataService;

    public UsersServiceImpl(final UserRepository userRepository, final CommonDataService commonDataService) {
        this.userRepository = userRepository;
        this.commonDataService = commonDataService;
    }

    @Override
    public User createUser(User user) {
        User existingUser = userRepository.findByUserId(user.getUserId());
        if (existingUser != null)
            throw new APIException("User already exists");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User savedUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exists for update"));
        String userName = user.getName();
        if (!savedUser.getName().equals(userName)) {
            commonDataService.updateUserByName(userName, savedUser.getName());
            savedUser.setName(userName);
        }
        savedUser.setUserId(user.getUserId());
        log.info(String.format("Saving user with user name: %s", user.getName()));
        return userRepository.save(savedUser);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
