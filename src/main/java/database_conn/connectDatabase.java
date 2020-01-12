package database_conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDatabase {

    private String url="jdbc:postgresql://localhost/hospitalae";
    private Connection conn ;

    protected String username=new databaseLoginDialog().getUser();
    protected String password=new databaseLoginDialog().getPass();

    public connectDatabase() throws SQLException{

        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.getMessage();
        }

        conn=DriverManager.getConnection(url, username, password);
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
