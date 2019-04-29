package mate.dao;

import mate.exception.ThisLoginIsExistException;
import mate.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DatabaseUserDaoTest {
    private UserDao userDao = new DatabaseUserDao();
    private User testUser = new User("TestName", "password", "test@mail.ua", "USA");
    private User testUser2 = new User("TestName2", "password2", "2test@mail.ua", "2USA");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() throws ThisLoginIsExistException {
        userDao.addUser(testUser);
    }

    @After
    public void after() {
        userDao.deleteUser(userDao.getUser(testUser.getLogin()).get().getId());
        if (userDao.contains(testUser2.getLogin())) {
            userDao.deleteUser(userDao.getUser(testUser2.getLogin()).get().getId());
        }
    }

    @Test
    public void addUser() throws ThisLoginIsExistException {
        userDao.addUser(testUser2);
        thrown.expect(ThisLoginIsExistException.class);
        userDao.addUser(testUser2);
    }

    @Test
    public void getUser() {
        Optional<User> user = userDao.getUser(testUser.getLogin());

        User object = user.get();
        Assert.assertNotNull(object);
        Assert.assertEquals(testUser.getLogin(), object.getLogin());
        Assert.assertEquals(testUser.getPassword(), object.getPassword());
        Assert.assertEquals(testUser.getCountry(), object.getCountry());
        Assert.assertEquals(testUser.getEmail(), object.getEmail());

    }

    @Test
    public void updateUser() {
        String country = "Ukraine";
        Optional<User> optionalUser = userDao.getUser(testUser.getLogin());
        User user = optionalUser.get();
        user.setCountry(country);

        userDao.updateUser(user);
        Optional<User> optional = userDao.getUser(user.getLogin());

        Assert.assertNotNull(optional.get());
        Assert.assertEquals(country, optional.get().getCountry());
    }

    @Test
    public void deleteUser() {
        userDao.deleteUser(userDao.getUser(testUser.getLogin()).get().getId());
        thrown.expect(NoSuchElementException.class);
        userDao.getUser(testUser.getLogin());
    }

    @Test
    public void contains() {
        Assert.assertTrue(userDao.contains(testUser.getLogin()));
        Assert.assertTrue(userDao.contains(testUser.getLogin(), testUser.getPassword()));
    }

}