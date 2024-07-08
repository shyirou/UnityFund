package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class config {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/orange";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final Logger LOGGER = Logger.getLogger(config.class.getName());

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Server Database Tidak Aktif", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        LOGGER.info("Terkoneksi ke Database");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
