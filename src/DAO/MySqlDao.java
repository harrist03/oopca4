package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Exceptions.DaoException;

public class MySqlDao {
    public Connection getConnection() throws DaoException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oopca4";
        String userName = "harris";
        String password = "harris12345";

        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.exit(1);
        } catch (SQLException e) {
            System.out.println(
                    "Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
            System.exit(2);
        }

        return conn;
    }

    public void freeConnection(Connection conn) throws DaoException {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
