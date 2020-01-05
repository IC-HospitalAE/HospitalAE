package database_conn;
import java.sql.*;

public class connectDatabase {

    private String url="jdbc:postgresql://localhost/hospitalae?currentSchema=public&user=postgres&password=password";

    private Connection conn ;

    public connectDatabase() throws SQLException {
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.getMessage();
        }
        conn=DriverManager.getConnection(url,"postgres","password");
    }

    public Connection getConnection() throws SQLException {
        return conn;
    }

    public Statement createStatement() throws SQLException {
       return conn.createStatement();
    }

    public void close() throws SQLException {
        conn.close();
    }
}
