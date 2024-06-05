package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public static final String sqlCreateUT = "CREATE TABLE IF NOT EXISTS Users " +
            "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(40) NOT NULL, " +
            "last_name VARCHAR(40) NOT NULL, " +
            "age INT NOT NULL)";
    public static final String sqlDropUT = "DROP TABLE IF EXISTS Users";
    public static final String sqlSaveUser = "INSERT INTO Users (name, last_name, age) VALUES (?, ?, ?)";
    public static final String sqlRemoveUser = "DELETE FROM Users WHERE id = ?";
    public static final String sqlGetAllUsers = "SELECT id, name, last_name, age FROM Users";
    public static final String sqlCleanUT = "TRUNCATE TABLE Users";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateUT);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDropUT)) {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveUser)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAllUsers);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCleanUT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
