package mate.servlets.dao;

import mate.servlets.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getUsers();
    boolean contains(String login);
    boolean contains(String login, String pass);
}
