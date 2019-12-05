import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class sendemail {
    public static void main(String[] args) throws SQLException {

        //Start to find data in sql
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";

        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {

        }

        Connection conn = DriverManager.getConnection(dbUrl, "postgres", "Tchz1998!");


        try {
            Statement s = conn.createStatement();
            String sqlStr = "SELECT * FROM projectdb.public.doctors WHERE email = 'hongzhang.chen17@imperial.ac.uk';";
            ResultSet r = s.executeQuery(sqlStr);

            while (r.next()) {
                System.out.println(r.getString("email"));

            }
            r.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("something went wrong!");
        }


        String to = "hongzhang.chen17@imperial.ac.uk";//change accordingly
        String from = "chenhongzhang98@gmail.com";//change accordingly
        String host = "localhost";//or IP address


        //Get the session object
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chenhongzhang98@gmail.com", "gkmajbmqvmuhyzdi");
            }
        });

        //compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your Hospital Needs You");
            message.setText("There is a shortage of doctors in the ward, please come in and help. Thank you!");

            // Send message
            Transport.send(message);
            System.out.println("message sent successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }




    }
}



