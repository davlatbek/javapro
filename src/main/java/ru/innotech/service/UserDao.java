package ru.innotech.service;

import ru.innotech.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(Long id, String newName);

    void updateUser(Long id, String newName);

    void deleteUser(Long id);

    User getUserById(Long id);

    Integer maxIdUsers();
}
