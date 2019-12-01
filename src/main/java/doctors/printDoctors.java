package doctors;

import UI.countRowsRequired;
import UI.setupFrame;
import database_conn.connectDatabase;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URISyntaxException;
import java.sql.*;

public class printDoctors {
    private setupFrame frame =new setupFrame();
    private JPanel mainPanel=new JPanel(new BorderLayout(8,8));
    private JPanel drPrintNames;
    private JLabel dr_first,dr_lastname,dr_email,dr_idnum,dr_workload,dr_availability;
    boolean avail;

    private JLabel fNameTitle,lastNameTitle,emailT,idT,workloadT,statusT;

    public void printD() throws SQLException, URISyntaxException {

        frame.setFrame();
        frame.setTitle("All doctors");
        frame.getFrame();

        GridLayout gl = new GridLayout();
        gl.setRows(2); //this is just to initialise //doesnt matter what value
        drPrintNames = new JPanel(gl);
        drPrintNames.setBackground(new Color(236, 240, 241));
        drPrintNames.setBorder(new LineBorder(Color.BLACK,2));

        labelNames();

        connectDatabase conn= new connectDatabase();
        conn.getConnection();


        try {
            Statement s = conn.createStatement();
            String sqlStr = "SELECT * FROM doctors WHERE id>=1;";
            ResultSet rset = s.executeQuery(sqlStr);

            //to ge the number of patients
            Statement s2 = conn.createStatement();
            String sqlStr2 = "SELECT count(id) FROM doctors ";
            ResultSet rset2 = s2.executeQuery(sqlStr2);

            countRowsRequired num=new countRowsRequired();
            num.NumofRows_plus1(gl, rset2);

            while(rset.next()){

                setupLabels(rset);
                if(!avail){
                    dr_availability =new JLabel("Not in duty");
                }else
                    dr_availability =new JLabel("In duty");

                addDrToPanel();

                mainPanel.add(drPrintNames);

                frame.add(mainPanel);
            }
            rset.close();
            s.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        JScrollPane scroll =new JScrollPane(drPrintNames,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scroll);
    }

    private void addDrToPanel() {
        drPrintNames.add(dr_first);
        drPrintNames.add(dr_lastname);
        drPrintNames.add(dr_idnum);
        drPrintNames.add(dr_email);
        drPrintNames.add(dr_workload);
        drPrintNames.add(dr_availability);
    }
    private void setupLabels(ResultSet rset) throws SQLException {
        dr_first=new JLabel(rset.getString("firstname"));
        dr_lastname=new JLabel(rset.getString("lastname"));
        dr_email=new JLabel(rset.getString("email"));
        dr_idnum=new JLabel(rset.getString("identitynumber"));
        dr_workload=new JLabel("         "+rset.getString("workload"));
        avail=rset.getBoolean("availability");
    }
    private void labelNames(){
        fNameTitle=new JLabel("First name");
        lastNameTitle=new JLabel("Family name");
        idT=new JLabel("ID");
        emailT=new JLabel("Email");
        workloadT=new JLabel("Work hours");
        statusT= new JLabel("Availability");

        drPrintNames.add(fNameTitle);
        drPrintNames.add(lastNameTitle);
        drPrintNames.add(idT);
        drPrintNames.add(emailT);
        drPrintNames.add(workloadT);
        drPrintNames.add(statusT);
    }

}
