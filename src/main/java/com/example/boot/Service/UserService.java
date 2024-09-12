package com.example.boot.Service;

import com.example.boot.Entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getData(Long id);

    List<User> getAllData();

    User updateUser(User user);

    void deleteUser(Long id);
}
