package com.app.services;

import com.app.entites.Environment;
import com.app.entites.User;
import com.app.exceptions.APIException;
import com.app.repositories.EnvironmentRepository;
import com.app.repositories.KeysRepository;
import com.app.repositories.PropertiesRepository;
import com.app.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Transactional
@Service
public class CommonDataService {

    private final PropertiesRepository propertiesRepository;
    private final KeysRepository keysRepository;
    private final EnvironmentRepository environmentRepository;
    private final UserRepository userRepository;

    public CommonDataService(final PropertiesRepository propertiesRepository, final KeysRepository keysRepository,
                             final EnvironmentRepository environmentRepository, UserRepository userRepository) {
        this.propertiesRepository = propertiesRepository;
        this.keysRepository = keysRepository;
        this.environmentRepository = environmentRepository;
        this.userRepository = userRepository;
    }

    public void deleteEnvironment(String name) {
        keysRepository.deleteEnvironment(name);
        propertiesRepository.deleteEnvironment(name);
    }

    public void updateUserByName(String userName, String savedUserName) {
        keysRepository.updateUserByName(userName, savedUserName);
        propertiesRepository.updateUserByName(userName, savedUserName);
    }

    public void updateEnvironmentByName(String name, String savedName) {
        keysRepository.updateEnvironmentByName(name, savedName);
        propertiesRepository.updateEnvironmentByName(name, savedName);
    }

    public boolean isEnvironmentExists(String name) {
        if (StringUtils.isEmpty(name))
            return true;
        Environment environment = environmentRepository.findByName(name);
        if (environment == null)
            throw new APIException("Environment doesn't exists with environment name: " + name);
        return true;
    }

    public boolean isUserExists(String name) {
        if (StringUtils.isEmpty(name))
            return true;
        User user = userRepository.findByName(name);
        if (user == null)
            throw new APIException("User doesn't exists with user name: " + name);
        return true;
    }
}
