import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class client_connection {

    public static void main(String[] args) throws IOException, SQLException {
        GetRequest(); //Calls GetRequest Method
        PostRequest(); //Calls PostRequest Method
    }

    public static void GetRequest() { //GetRequest Method
        //GET Request URL of Heroku (request data from specified source) alan
        try {
            URL myURL = new URL("http://localhost:8080/Servlet123/patients");
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            //Reading Input // Dont think we need it in our case
            BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
            String inputLine;
            // Read the body of the response
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void PostRequest() throws SQLException { //Post Method is used to send data  to the servlet(doctor page) to update the resource

        try {  //getting URL of local database (Receptionist page)
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";  //Establishing the connection to the database
            ResultSet rset;
            ResultSet rset1;
            try {// Registers the driver //Loading the driver
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
            }
            try { //SQL that returns instructions
                //Accessing database named patients or doctors and SELECT query
                //Querying SQL database
                //Performing a SQL Query against a database
                Connection conn = DriverManager.getConnection(dbUrl, "postgres", "Wenli123"); //Setting up localhost username and password
                //Creating a SQL Query
                String sqlStr = "SELECT * FROM patients;";  //Statement returns name,familyname.. from the patients database
                String sqlStr1 = "SELECT * FROM doctors;";  //Statement returns name,familyname.. from the doctors database
                Statement s = conn.createStatement();  //Creating a Java Statement objection from the Connection object.
                Statement s1 = conn.createStatement();
                rset = s.executeQuery(sqlStr); //The ResultsSet object rset contains the result from the database query.
                rset1 = s1.executeQuery(sqlStr1);

                while (rset.next()) { //Reading the results  from rset(patient info) after we execute the SQL Query. Loops reads the family name, givenname
                    // returned in each record and prints it to the screen

                    String family_name = rset.getString("familyname");
                    System.out.println(family_name);
                    String given_name = rset.getString("givenname");
                    System.out.println(given_name);
                    String phone_n = rset.getString("phonenumber");
                    System.out.println(phone_n);

                    // Set up the body data  for family name
                    byte[] body = family_name.getBytes(StandardCharsets.UTF_8);
                    URL myURL = new URL("http://localhost:8080/Servlet123/patients");
                    HttpURLConnection connet = null;
                    connet = (HttpURLConnection) myURL.openConnection();
                    System.out.println("Connected");
                    // Set up the header
                    connet.setRequestMethod("POST");
                    connet.setRequestProperty("Accept", "text/html");
                    connet.setRequestProperty("charset", "utf-8");
                    connet.setRequestProperty("Content-Length", Integer.toString(body.length));
                    connet.setDoOutput(true);

                    try (OutputStream outputStream = connet.getOutputStream()) {
                        outputStream.write(body, 0, body.length);
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connet.getInputStream(), "utf-8"));
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                    bufferedReader.close();

                    // Set up the body data  for given name
                    byte[] body1 = given_name.getBytes(StandardCharsets.UTF_8);
                    URL myURL1 = new URL("http://localhost:8080/Servlet123/patients");
                    HttpURLConnection connet1 = null;
                    connet1 = (HttpURLConnection) myURL1.openConnection();
                    System.out.println("Connected");
                    // Set up the header
                    connet1.setRequestMethod("POST");
                    connet1.setRequestProperty("Accept", "text/html");
                    connet1.setRequestProperty("charset", "utf-8");
                    connet1.setRequestProperty("Content-Length", Integer.toString(body1.length));
                    connet1.setDoOutput(true);
                    try (OutputStream outputStream = connet1.getOutputStream()) {
                        outputStream.write(body1, 0, body1.length);
                    }

                    BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(connet1.getInputStream(), "utf-8"));
                    String inputLine1;
                    while ((inputLine1 = bufferedReader1.readLine()) != null) {
                        System.out.println(inputLine1);
                    }
                    bufferedReader.close();
                    // Set up the body data  for phone number
                    byte [] body2 = phone_n.getBytes(StandardCharsets.UTF_8);
                    URL myURL2 = new URL("http://localhost:8080/Servlet123/patients");
                    HttpURLConnection connet2 = null;
                    connet2 = (HttpURLConnection) myURL2.openConnection();
                    System.out.println("Connected");
                    // Set up the header
                    connet2.setRequestMethod("POST");
                    connet2.setRequestProperty("Accept", "text/html");
                    connet2.setRequestProperty("charset", "utf-8");
                    connet2.setRequestProperty("Content-Length", Integer.toString(body2.length));
                    connet2.setDoOutput(true);
                    try (OutputStream outputStream = connet2.getOutputStream()) {
                        outputStream.write(body2, 0, body2.length);
                    }

                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(connet2.getInputStream(),"utf-8"));
                    String inputLine2;
                    while((inputLine2 = bufferedReader2.readLine()) != null){
                        System.out.println(inputLine2);
                    }
                    bufferedReader2.close();
                }

                  rset.close();
                  s.close();
                  conn.close();


                while (rset1.next()) { //Reading the results  from rset1(doctor info) after we execute the SQL Query. Loops reads the family name, givenname
                    // returned in each record and prints it to the screen
                    String doctor_name = rset1.getString("familyname");
                    System.out.println(doctor_name);
                    String doctor_gname = rset1.getString("givenname");
                    System.out.println(doctor_gname);

                    // Set up the body data

                    byte[] body = doctor_name.getBytes(StandardCharsets.UTF_8);
                    URL myURL = new URL("http://localhost:8080/Servlet123/patients");
                    HttpURLConnection connet = null;
                    connet = (HttpURLConnection) myURL.openConnection();
                    System.out.println("Connected");
                    // Set up the header
                    connet.setRequestMethod("POST");
                    connet.setRequestProperty("Accept", "text/html");
                    connet.setRequestProperty("charset", "utf-8");
                    connet.setRequestProperty("Content-Length", Integer.toString(body.length));
                    connet.setDoOutput(true);
                    try (OutputStream outputStream = connet.getOutputStream()) {
                        outputStream.write(body, 0, body.length);
                    }

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connet.getInputStream(), "utf-8"));
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                    bufferedReader.close();


                      // Set up the body data
                    byte[] body1 = doctor_gname.getBytes(StandardCharsets.UTF_8);
                    URL myURL1 = new URL("http://localhost:8080/Servlet123/patients");
                    HttpURLConnection connet1 = null;
                    connet1 = (HttpURLConnection) myURL1.openConnection();
                    System.out.println("Connected");
                    // Set up the header
                    connet1.setRequestMethod("POST");
                    connet1.setRequestProperty("Accept", "text/html");
                    connet1.setRequestProperty("charset", "utf-8");
                    connet1.setRequestProperty("Content-Length", Integer.toString(body1.length));
                    connet1.setDoOutput(true);
                    try (OutputStream outputStream = connet1.getOutputStream()) {
                        outputStream.write(body1, 0, body1.length);
                    }

                    BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(connet1.getInputStream(), "utf-8"));
                    String inputLine1;
                    while ((inputLine1 = bufferedReader1.readLine()) != null) {
                        System.out.println(inputLine1);
                    }
                    bufferedReader.close();


                }
                rset1.close();
                s1.close();
                conn.close();


            } catch (Exception e) {
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}