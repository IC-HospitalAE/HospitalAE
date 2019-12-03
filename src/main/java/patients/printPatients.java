package patients;
import UI.countRowsRequired;
import UI.setupFrame;
import bed.assignBed;
import database_conn.connectDatabase;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URISyntaxException;
import java.sql.*;

public class printPatients {

    //declatation of vars
    private setupFrame frame =new setupFrame();

    private JPanel mainPanel=new JPanel(new BorderLayout(8,8));
    private JPanel patientPrintPanel;

    private JLabel patientFirstName,patientFamilyName,patientAge,patientIDNum,patientNameNotes,patientStatus;

    private boolean pStatus;
    private JLabel fNameTitle,famNameTitle,ageT,idT,notesT,statusT;

    public void printP() throws SQLException, URISyntaxException {

        GridLayout gl=new GridLayout();
        gl.setRows(2); //this is just to initialise //doesnt matter what value

        patientPrintPanel=new JPanel(gl);
        patientPrintPanel.setBackground(new Color(236, 240, 241));
        patientPrintPanel.setBorder(new LineBorder(Color.BLACK,2));

        //setup the frame
        frame.setFrame();
        frame.setTitle("All patients");
        frame.getFrame();

        labelNames();

        //connect to db
        connectDatabase conn= new connectDatabase();
        conn.getConnection();

        try {
            Statement s=conn.createStatement();
            String sqlStr = "SELECT * FROM patients WHERE id>=1;";
            ResultSet rset=s.executeQuery(sqlStr);

            //to get the number of patients
            Statement s2=conn.createStatement();
            String sqlStr2="SELECT count(id) FROM patients;";
            ResultSet rset2=s2.executeQuery(sqlStr2);

            //num of rows to print the labels correctly
            countRowsRequired num=new countRowsRequired();
            num.NumofRows_plus1(gl, rset2);

            //gets patient info from db
            while(rset.next()){

                setLabels(rset);

                if(!pStatus){
                    patientStatus=new JLabel("Discharged");
                }else
                    patientStatus=new JLabel("Admitted");

                addPatientToPanel();

                mainPanel.add(patientPrintPanel);

                frame.add(mainPanel);
            }
            rset.close();
            s.close();
            conn.close();
        }catch (Exception e){

        }
        JScrollPane scroll=new JScrollPane(patientPrintPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scroll);
    }

    private void addPatientToPanel() {
        patientPrintPanel.add(patientFirstName);
        patientPrintPanel.add(patientFamilyName);
        patientPrintPanel.add(patientAge);
        patientPrintPanel.add(patientIDNum);
        patientPrintPanel.add(patientNameNotes);
        patientPrintPanel.add(patientStatus);
    }
    private void setLabels(ResultSet rset) throws SQLException {
        patientFirstName=new JLabel(rset.getString("givenname"));
        patientFamilyName=new JLabel(rset.getString("familyname"));
        patientAge=new JLabel(rset.getString("age"));
        patientIDNum=new JLabel(rset.getString("identitynumber"));
        patientNameNotes=new JLabel(rset.getString("notes"));
        pStatus=rset.getBoolean("admit_status");
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
        patientPrintPanel.add(statusT);
    }

}
