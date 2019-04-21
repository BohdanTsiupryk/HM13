package mate.servlets.dao;

import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryUserDaoTest {
    private static UserDao userDao;
    private User ivan = new User("Ivan", "abcde");
    private User stepan = new User("Stepan", "qwerty");
    private User bohdan = new User("Bohdan", "123456");

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() throws ThisLoginIsExistException {
        userDao = new InMemoryUserDao();
        userDao.addUser(bohdan);
        userDao.addUser(ivan);
        userDao.addUser(stepan);
    }

    @Test
    public void addUser() throws ThisLoginIsExistException{
        User newUser = new User("Pavlo", "theBestPassEver");

        userDao.addUser(newUser);
        Assert.assertEquals(newUser, userDao.getUsers().get(3));

        thrown.expect(ThisLoginIsExistException.class);
        userDao.addUser(new User("Ivan", "theBestPassEver"));
    }

    @Test
    public void getUsers() {
        List<User> users = new ArrayList<>(Arrays.asList(bohdan, ivan, stepan));

        List<User> userDaoUsers = userDao.getUsers();
        Assert.assertTrue(userDaoUsers.equals(users));
    }

    @Test
    public void contains() {
        Assert.assertTrue(userDao.contains("Bohdan"));
        Assert.assertFalse(userDao.contains("Evgen"));
    }

    @Test
    public void contains1() {
        Assert.assertTrue(userDao.contains("Bohdan","123456"));
        Assert.assertFalse(userDao.contains("Bohdan","notTheBestPass"));
    }
}