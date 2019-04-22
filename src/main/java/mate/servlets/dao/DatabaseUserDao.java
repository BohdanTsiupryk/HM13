package mate.servlets.dao;

import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUserDao implements UserDao {
    @Override
    public void addUser(User user) throws ThisLoginIsExistException {
        String sqlQuery = "INSERT INTO users(login,pass) VALUES ('"
                + user.getLogin() + "','" + user.getPassword() + "');";
        String sqlQueryGet = "SELECT * FROM users WHERE login = '" + user.getLogin() + "';";

        try (Connection connection = DbConnector.getConnection();
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQueryGet);

            if (resultSet.next()) {
                throw new ThisLoginIsExistException();
            }

            statement.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        String sqlQuery = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();

        try (Connection connection = DbConnector.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                users.add(new User(resultSet.getString("login"),resultSet.getString("pass")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean contains(String login) {
        String sqlQueryGet = "SELECT * FROM users;";

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQueryGet);

            if (resultSet.next()) {
                if (resultSet.getString(1).equals(login)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(String login, String pass) {
        String sqlQueryGet = "SELECT * FROM users;";

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQueryGet);

            if (resultSet.next()) {
                if (resultSet.getString(1).equals(login)) {
                    if (resultSet.getString(2).equals(pass)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
