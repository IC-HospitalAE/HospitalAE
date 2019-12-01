package database_conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDatabase {

    private String dbUrl = "jdbc:postgresql://localhost:5432/project";
    private Connection conn = DriverManager.getConnection(dbUrl);

    public connectDatabase() throws SQLException {
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.getMessage();
        }
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
