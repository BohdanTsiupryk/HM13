package mate.servlets.model;

import mate.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void init() {
        user = new User("bohdan", "123");
    }

    @Test
    public void getLogin() {
        String login = "bohdan";
        Assert.assertEquals(login, user.getLogin());
    }

    @Test
    public void getPassword() {
        String pass = "123";
        Assert.assertEquals(pass, user.getPassword());
    }
}