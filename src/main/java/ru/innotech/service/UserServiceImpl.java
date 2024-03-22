package ru.innotech.service;

import ru.innotech.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(Long id, String name) {
        userDao.addUser(id, name);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(Long id, String newName) {
        userDao.updateUser(id, newName);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
