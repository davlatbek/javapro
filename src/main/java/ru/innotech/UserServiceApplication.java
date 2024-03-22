package ru.innotech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.innotech.service.UserServiceImpl;

public class UserServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru/innotech");

        UserServiceImpl userServiceImpl = context.getBean(UserServiceImpl.class);
        userServiceImpl.createUser(1L, "anna");
        userServiceImpl.createUser(2L, "rick");
        userServiceImpl.createUser(3L, "ronald");
        userServiceImpl.createUser(4L, "vladimir");
        userServiceImpl.createUser(5L, "lizzy");
        userServiceImpl.createUser(6L, "clark");

        System.out.println(userServiceImpl.getAllUsers());
        System.out.println("Обновить инфо о пользователе, id=1");
        userServiceImpl.updateUser(1L, "Sasha");
        System.out.println("Удалить пользователя, id=6");
        userServiceImpl.deleteUser(6L);
        System.out.println(userServiceImpl.getAllUsers());
        System.out.println("Получить пользователя по id =  2 "
                + userServiceImpl.getUserById(2L));
    }

}
