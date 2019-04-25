package mate.servlets.dao;

import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseUserDao implements UserDao {
    private String sqlGetAll = "SELECT * FROM users;";

    @Override
    public void addUser(User user) throws ThisLoginIsExistException {
        String sqlQuery = "INSERT INTO users(login,pass,email,country) VALUES ('"
                + user.getLogin() + "','" + user.getPassword() + "','"
                + user.getEmail() + "','" + user.getCountry() + "');";
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
        List<User> users = new ArrayList<>();

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlGetAll);

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("pass"), resultSet.getString("email"),
                        resultSet.getString("country")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> getUser(String login) {
        String sqlGetOne = "SELECT * FROM users WHERE login = '" + login + "';";

        try (Connection connection = DbConnector.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlGetOne);
            if (rs.next()) {
                return Optional.of(new User(rs.getInt("id"),
                        rs.getString("login"), rs.getString("pass"),
                        rs.getString("email"), rs.getString("country")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {
        String sqlUpdateUser = "UPDATE users SET login = ?, pass = ?, email = ?, country = ? WHERE id = ?;";
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdateUser)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCountry());
            statement.setInt(5, user.getID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String sqlDeleteUser = "DELETE FROM users WHERE id=" + id + ";";

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {

           statement.execute(sqlDeleteUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(String login) {
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlGetAll);

            while (resultSet.next()) {
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
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlGetAll);

            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login)) {
                    if (resultSet.getString("pass").equals(pass)) {
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
