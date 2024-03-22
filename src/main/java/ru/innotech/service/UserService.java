package ru.innotech.service;

import ru.innotech.model.User;

import java.util.List;

public interface UserService {

    void createUser(Long id, String name);

    void deleteUser(Long id);

    List<User> getAllUsers();

    void updateUser(Long id, String newName);

    User getUserById(Long id);
}
