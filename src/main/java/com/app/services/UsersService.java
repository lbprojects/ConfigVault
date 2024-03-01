package com.app.services;


import com.app.entites.User;

import java.util.List;

public interface UsersService {

    User createUser(User user);
    User updateUser(Long id, User user);
    User getUser(Long id);
    List<User> getUsers();
}
