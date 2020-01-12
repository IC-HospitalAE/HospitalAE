package database_conn;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;

public class connectDatabase {

    private String url="jdbc:postgresql://localhost/hospitalae";
    private Connection conn ;

    private String username;
    private String password;

    public connectDatabase() throws SQLException, IOException, URISyntaxException {
       initialiseDB iniDB=new initialiseDB();
       username=iniDB.getUser();
       password=iniDB.getPass();

        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.getMessage();
        }
        conn=DriverManager.getConnection(url,username,password);
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
