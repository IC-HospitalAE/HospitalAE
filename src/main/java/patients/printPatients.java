package patients;
import UI.MenuBar;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class printPatients {

    private JFrame frame;
    private JPanel patientPrintPanel;
    private JLabel patientFirstName,patientFamilyName,patientAge,patientIDNum,patientNameNotes,patientStatus;

    private JLabel fNameTitle,famNameTitle,ageT,idT,notesT,statusT;

    public void printP() throws SQLException {
        patientPrintPanel=new JPanel(new GridLayout(10,10));
        patientPrintPanel.setBackground(new Color(236, 240, 241));

        setupFrame();
        labelNames();

        String dbUrl= "jdbc:postgresql://localhost:5432/project";
        Connection conn= DriverManager.getConnection(dbUrl);

        try {
            Statement s=conn.createStatement();
            String sqlStr = "SELECT * FROM patients WHERE id>=1;";
            ResultSet rset=s.executeQuery(sqlStr);
            while(rset.next()){

                patientFirstName=new JLabel(rset.getString("givenname"));
                patientFamilyName=new JLabel(rset.getString("familyname"));
                patientAge=new JLabel(rset.getString("age"));
                patientIDNum=new JLabel(rset.getString("identitynumber"));
                patientNameNotes=new JLabel(rset.getString("notes"));

                //add patient status

                patientPrintPanel.add(patientFirstName);
                patientPrintPanel.add(patientFamilyName);
                patientPrintPanel.add(patientAge);
                patientPrintPanel.add(patientIDNum);
                patientPrintPanel.add(patientNameNotes);
//                patientPrintPanel.add(patientStatus);

                frame.getContentPane().add(patientPrintPanel);

                System.out.println(rset.getInt("id")+" "+ rset.getString("givenname")+" "+ rset.getString("familyname"));
            }
            rset.close();
            s.close();
            conn.close();
        }
        catch (Exception e){
        }


    }

    private void labelNames(){
        fNameTitle=new JLabel("First name: ");
        famNameTitle=new JLabel("Family name: ");
        ageT=new JLabel("Age: ");
        idT=new JLabel("Identity number: ");
        notesT=new JLabel("Notes: ");
        statusT=new JLabel("Status");

        patientPrintPanel.add(fNameTitle);
        patientPrintPanel.add(famNameTitle);
        patientPrintPanel.add(ageT);
        patientPrintPanel.add(idT);
        patientPrintPanel.add(notesT);
        //patientPrintPanel.add(statusT);
    }

    private void setupFrame(){
        frame = new JFrame("All Patients List");
        frame.setSize(500, 600);
        frame.setJMenuBar(new MenuBar());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(2);
    }

}
