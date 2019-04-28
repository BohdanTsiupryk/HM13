package mate.servlets.dao;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DbConnectorTest {

    @Test
    public void getConnection() throws SQLException {
        Connection connection = DbConnector.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}