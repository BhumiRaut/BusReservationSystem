package BusReservation.BusResevation;

import java.sql.*;

public class DBConnection2 {
    private static final String URL = "jdbc:postgresql://localhost:5432/your_db";
    private static final String USER = "your_user";
    private static final String PASS = "your_pass";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Postgres JDBC driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}