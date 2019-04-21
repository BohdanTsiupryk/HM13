package mate.servlets.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user = new User("bohdan", "123");

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