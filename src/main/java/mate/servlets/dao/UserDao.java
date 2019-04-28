package mate.servlets.dao;

import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user) throws ThisLoginIsExistException;
    List<User> getUsers();
    Optional<User> getUser(String login);
    void updateUser(User user);
    void deleteUser(int id);
    boolean contains(String login);
    boolean contains(String login, String pass);
}
