package mate.servlets.dao;

import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user) throws ThisLoginIsExistException;
    List<User> getUsers();
    boolean contains(String login);
    boolean contains(String login, String pass);
}
