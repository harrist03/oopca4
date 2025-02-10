import java.sql.*;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        String url = "jdbc:mysql://localhost/";
        String dbName = "oopca4";
        String userName = "harris";
        String password = "harris12345";

        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password)) {
            System.out.println("Connected to database");

        } catch (SQLException ex) {
            System.out.println(
                    "Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
            ex.printStackTrace();
        }
    }
}
