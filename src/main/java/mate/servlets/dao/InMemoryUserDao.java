package mate.servlets.dao;

import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {

    private final List<User> USERS = new ArrayList<User>();

    public void addUser(User user) throws ThisLoginIsExistException {
        if (!contains(user.getLogin())) {
            USERS.add(user);
        } else {
            throw new ThisLoginIsExistException();
        }
    }

    public List<User> getUsers() {
        return USERS;
    }

    public boolean contains(String login) {
        return USERS.stream()
                .anyMatch(o -> o.getLogin().equals(login));
    }

    public boolean contains(String login, String pass) {
        return USERS.stream()
                .filter(o -> o.getLogin().equals(login))
                .findFirst()
                .orElse(new User(null,"")).getPassword().equals(pass);
    }
}
