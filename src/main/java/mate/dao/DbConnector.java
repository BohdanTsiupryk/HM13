package mate.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final Logger log = Logger.getLogger(DbConnector.class);
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/metaacademy";
    private static final String USER = "bohdan";
    private static final String PASSWORD = "1698";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
