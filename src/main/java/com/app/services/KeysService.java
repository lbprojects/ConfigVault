package com.app.services;


import com.app.entites.Key;

import java.util.List;

public interface KeysService {

    Key createKey(Key key);
    Key updateKey(Long id, Key key);
    Key getKey(Long id);
    void deleteKey(Long id);
    List<Key> getAllKeys();
    List<Key> getAllKeyByEnvironment(String name);
    List<Key> getAllByKeyName(String name);
}
