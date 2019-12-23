package doctors;
import database_conn.connectDatabase;

import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


// The following conditions must be satisfied for this method to be executed

// 1 new patient checks in
// 2 doctor shortage
// 3 doctor should have less than ten shifts
// 4 doctor should not work the previous shift or the next shift

public class sendEmail {

        //Start to find data in sql
    public sendEmail() throws SQLException {
        connectDatabase conn=new connectDatabase();
        try {

            //run sql string to select doctors meeting the conditions
            //Conditions:
            //1. new patient checks in (should be an if statement in reception app main())
            //1. doctor is available DONE HERE
            //3. doctor has less than 10 shifts DONE HERE
            //4. doctor should not work the previous shift or the next shift (should be linked to the doctors timetable)
            Statement s1 = conn.createStatement();
            String sqlStr = "SELECT email FROM doctors WHERE availability = 'false' AND workload < '10';";
            ResultSet r = s1.executeQuery(sqlStr);

            while (r.next()) {
                String to = r.getString("email");//change accordingly
                String from = "chenhongzhang98@gmail.com";//send emails from this email address
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
                    //Here is the email title
                    message.setSubject("Help needed in ward 1!");
                    //Here is the email body
                    message.setText("There is a shortage of doctors in the ward, please come in due to an emergency.\n\nThank you!\nRegards,\nReception");

                    // Send message
                    Transport.send(message);
                    System.out.println("message sent successfully...");

                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }


            }
            r.close();
            s1.close();
            conn.close();
        } catch (Exception e) {
            //print line if something goes wrong
            System.out.println("something went wrong!");
        }
    }

}



