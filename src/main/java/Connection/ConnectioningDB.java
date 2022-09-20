package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectioningDB {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/MusicalJukeboxts";
        String user = "root";
        String pass = "admin";
        return DriverManager.getConnection(url, user, pass);
    }
}
