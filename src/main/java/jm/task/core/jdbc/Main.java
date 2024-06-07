package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Coco", "Shanel", (byte) 87);
        User user2 = new User("Anton", "Chehov", (byte) 65);
        User user3 = new User("Vlad", "Tcepesh", (byte) 1);
        User user4 = new User("Donald", "Duck", (byte) 50);

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User: " + user1.getName() + " добавлен в базу данных");
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User: " + user2.getName() + " добавлен в базу данных");
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User: " + user3.getName() + " добавлен в базу данных");
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User: " + user4.getName() + " добавлен в базу данных");

        List<User> usersList = userService.getAllUsers();
        for (User user : usersList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

