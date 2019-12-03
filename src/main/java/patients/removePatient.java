package patients;
import UI.MenuBar;
import database_conn.connectDatabase;
import UI.setupFrame;
import UI.countRowsRequired;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.sql.*;
import bed.assignBed;

public class removePatient{
    private setupFrame frame =new setupFrame();
    private JPanel mainPanel=new JPanel(new GridLayout(3,5));
    private JPanel searchPanel=new JPanel(new GridLayout(5,5));
    private JPanel numPeople = new JPanel();
    private JPanel patientList; //lists all the patient(s) that the user typed

    private JButton searchbtn=new JButton("Search");
    private JButton dischargeBtn;

    private JLabel searchNameLabel;

    private JTextField nameField;

    private JLabel firstname,lastname,phonenumber,idnumber,numberofPatients,sucessLabel,bednumber;

    private int count=0;

    String nameEntered=new String();

    public removePatient() throws SQLException, URISyntaxException {

        //gridlayout for patient list
        GridLayout gl=new GridLayout();
        patientList=new JPanel(gl);

        //frame
        frame.setFrame();
        frame.setTitle("Discharge patients");
        frame.getFrame();

        //set up database
        connectDatabase conn=new connectDatabase();
        conn.getConnection();

        //labels n field declaration
        searchNameLabel=new JLabel("Enter name: ");
        nameField=new JTextField();

        sucessLabel=new JLabel("Patient discharged!");

        searchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //clears the JPanels

                patientList.removeAll();
                sucessLabel.setVisible(false);
                numPeople.removeAll();

                //get user input
                nameEntered = nameField.getText();

                try {
                    Statement s = conn.createStatement();
                    Statement s2 = conn.createStatement();
                    String sqlStr = "SELECT * FROM patients where givenname='" + nameEntered + "'";
                    ResultSet rset = s.executeQuery(sqlStr);

                    String sqlStr2 = "SELECT count(id) FROM patients WHERE givenname='" + nameEntered + "';";
                    ResultSet rset2 = s2.executeQuery(sqlStr2);

                    //gets num of rows the panel needs
                    countRowsRequired rowsRequired = new countRowsRequired();
                    rowsRequired.NumofRows(gl, rset2);

                    //counts the number of patients w same name by getting the number of rows
                    int count = rowsRequired.getCount();

                    String numPpl = "There are " + Integer.toString(count) + " patients named " + nameEntered + ".";
                    if (count > 1) {
                        numberofPatients = new JLabel(numPpl);
                    } else if (count == 0) {
                        numberofPatients = new JLabel("There are no patient named " + nameEntered + ".");
                    } else {
                        numberofPatients = new JLabel("There are " + Integer.toString(count) + " patient named " + nameEntered + ".");
                    }

                    numPeople.add(numberofPatients);
                    mainPanel.add(numPeople);
                    numPeople.add(sucessLabel);

                    while (rset.next()) {
                        firstname = new JLabel(rset.getString("givenname"));
                        lastname = new JLabel(rset.getString("familyname"));
                        phonenumber = new JLabel(rset.getString("phonenumber"));
                        idnumber = new JLabel(rset.getString("identitynumber"));

                        String bednum=rset.getString("bednumber");
                        bednumber=new JLabel(bednum);

                        patientList.add(firstname);
                        patientList.add(lastname);
                        patientList.add(idnumber);
                        patientList.add(phonenumber);
                        patientList.add(bednumber);

                        dischargeBtn = new JButton("Discharge");

                        dischargeBtn.addActionListener(actionEvent1 -> {
                            String sqlStr1= "UPDATE patients SET admit_status=false,bednumber='' WHERE givenname='" + nameEntered + "';";
                            String sqlStr5="UPDATE beds SET availability=true WHERE bed_id='"+bednum+"' ";
                                                                                                        //once patient is discharged
                            try {
                                assignBed setBedfree= new assignBed();
                                setBedfree.setBedAvailable(bednum);

                                s2.executeQuery(sqlStr1);
                                s2.execute(sqlStr5);
                                sucessLabel.setVisible(true);
                            } catch (SQLException | URISyntaxException e) {
                                // e.printStackTrace();
                            }
                        });

                        patientList.add(dischargeBtn);
                        mainPanel.revalidate();
                        mainPanel.add(patientList);
                    }
                } catch (Exception e) {
                }
            }
        });

        //search panel
        addtoSearchPanel();

        //main panel
        mainPanel.add(searchPanel);
        mainPanel.revalidate();
        frame.add(mainPanel);

    }

    private void addtoSearchPanel() {
        searchPanel.add(searchNameLabel);
        searchPanel.add(nameField);
        searchPanel.add(searchbtn);
    }
    public JPanel getMainPanel(){
        mainPanel.revalidate();
        return mainPanel;
    }

}