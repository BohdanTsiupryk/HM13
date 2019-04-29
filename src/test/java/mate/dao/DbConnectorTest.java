package mate.dao;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectorTest {

    @Test
    public void getConnection() throws SQLException {
        Connection connection = DbConnector.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}