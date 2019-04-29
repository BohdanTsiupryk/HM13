package mate.dao;

import mate.exception.ThisLoginIsExistException;
import mate.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseUserDao implements UserDao {
    private static final Logger log = Logger.getLogger(DatabaseUserDao.class);
    private String sqlGetAll = "SELECT * FROM users;";

    @Override
    public boolean addUser(User user) throws ThisLoginIsExistException {
        String sqlQueryAdd = "INSERT INTO users(login,pass,email,country) VALUES (?,?,?,?)";
        String sqlQueryGet = "SELECT * FROM users WHERE login = ?";

        try (Connection connection = DbConnector.getConnection();
             PreparedStatement statementAdd = connection.prepareStatement(sqlQueryAdd);
             PreparedStatement statementGet = connection.prepareStatement(sqlQueryGet)) {
            statementGet.setString(1, user.getLogin());

            statementAdd.setString(1, user.getLogin());
            statementAdd.setString(2, user.getPassword());
            statementAdd.setString(3, user.getEmail());
            statementAdd.setString(4, user.getCountry());

            log.debug("Get users sql: " + sqlQueryGet);
            log.debug("Update user sql: " + sqlQueryAdd);
            if (!statementGet.executeQuery().next()) {
                if (statementAdd.execute()) {
                    log.debug("Added user with login: " + user.getLogin());
                }
                return true;
            } else {
                log.debug("Trying to add user with nounic login: " + user.getLogin());
                throw new ThisLoginIsExistException();
            }

        } catch (SQLException e) {
            log.error("Can't add user to DB", e);
        }
        return false;
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
            log.error("Can't get users from DB", e);
        }
        return users;
    }

    @Override
    public Optional<User> getUser(String login) {
        String sqlGetOne = "SELECT * FROM users WHERE login = '" + login + "';";
        log.debug("Get user sql: " + sqlGetOne);
        try (Connection connection = DbConnector.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlGetOne);
            if (rs.next()) {
                log.debug("Get user from DB with login: " + login);
                return Optional.of(new User(rs.getInt("id"),
                        rs.getString("login"), rs.getString("pass"),
                        rs.getString("email"), rs.getString("country")));
            }
        } catch (SQLException e) {
            log.error("Can't get user from DB", e);
        }
        log.debug("User with login: " + login + "isn't exist");
        return Optional.empty();
    }

    @Override
    public boolean updateUser(User user) {
        String sqlUpdateUser = "UPDATE users SET login = ?, pass = ?, email = ?, country = ? WHERE id = ?;";
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdateUser)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCountry());
            statement.setInt(5, user.getId());

            log.debug("Update user sql:" + sqlUpdateUser);

            int update = statement.executeUpdate();

            if (update == 1) {
                log.debug("Successful update user with id:" + user.getId());
                return true;
            } else if (update > 1){
                log.error("Update more than one row");
                return false;
            }
        } catch (SQLException e) {
            log.error("Can't update user in DB", e);
        }

        log.debug("User whit login: " + user.getLogin() + ", id: " + user.getId() + " - isn't exist");
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        String sqlDeleteUser = "DELETE FROM users WHERE id=" + id + ";";
        log.debug("Delete SQL: " + sqlDeleteUser);

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {

            log.debug("Delete user with id: " + id);
            return statement.execute(sqlDeleteUser);
        } catch (SQLException e) {
            log.error("Can't delete user in DB", e);
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlGetAll);

            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login)) {
                    log.debug("User whit login: " + login + " is exist");
                    return true;
                }
            }
        } catch (SQLException e) {
            log.error("Login DB contains error", e);
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
                        log.debug("User whit login: " + login + " and pass is exist");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Login, password DB contains error", e);
        }
        return false;
    }
}
