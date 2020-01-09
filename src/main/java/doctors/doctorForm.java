package doctors;
import UI.setupFrame;
import database_conn.connectDatabase;
import JSON.Doctor;
import JSON.doctor_database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Statement;

public class doctorForm {
    private doctor_database dr_db=new doctor_database();
    private Doctor doctor;

    //frame class
    private setupFrame frame =new setupFrame();

    //jpanels to add components
    private JPanel mainPanel= new JPanel(new GridLayout(6,2));
    private JPanel buttonPanel=new JPanel(new GridLayout(1,10));

    //buttons
    private JButton p_btn = new JButton("Add new doctor");

    //text fields
    private JTextField nameField,familyField,emailField,IDField,workField;

    //initialise labels
    private JLabel nameLabel=new JLabel("First name:");
    private JLabel familyLabel=new JLabel("Family name:");
    private JLabel emailLabel=new JLabel("Email:");
    private JLabel IDLabel=new JLabel("ID number:");
    private final JLabel successLabel = new JLabel("Doctor added successfully!"); //prints only if patient is added

    //variables
    private String given_name=new String();
    private String familyname=new String();
    private String ID=new String();
    private String email=new String();

    public doctorForm() throws SQLException, URISyntaxException {

        //setting up frame
        frame.setFrame();
        frame.setTitle("Add doctor");
        frame.getFrame();

        //setup db connection
        connectDatabase conn= new connectDatabase();
        conn.getConnection();

        InitialiseformField();

        //initalise the label not to print wout the button pressed
        successLabel.setVisible(false);

        p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                given_name=nameField.getText();
                familyname=familyField.getText();
                ID=IDField.getText();
                email=emailField.getText();

                //add to Doctor class then send as JSON to cloud
                doctor=new Doctor(given_name,familyname,ID);

                //add to arraylist
                dr_db.addDoctor(doctor);
                dr_db.PrintDoctor(doctor);

                //add dr to postgres db
                try {
                    Statement s=conn.createStatement();
                    String sqlStr = "INSERT INTO public.doctors (firstname, lastname, identitynumber, email, workload, availability, num_patients, shift) " +
                            "values ('"+given_name+"','"+familyname+"','"+ID+"','"+email+"','',true,0,'')";
                    s.execute (sqlStr);
                    conn.close();
                }
                catch (Exception e){
                }

                //clears the text field after button pressed
                clearFields();

                //prints the label when button pressed
                successLabel.setVisible(true);
            }
        });

        //add all labels and text fields to main panel
        addToMainPanel();

        // add buttons to button panel
        buttonPanel.add(p_btn);
        buttonPanel.setPreferredSize(new Dimension(50,50));

        //add btn to main panel
        mainPanel.add(buttonPanel,BorderLayout.CENTER);

        mainPanel.revalidate();

        //add to frame
        frame.add(mainPanel);
    }

    private void addToMainPanel() {
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(familyLabel);
        mainPanel.add(familyField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(IDLabel);
        mainPanel.add(IDField);
        mainPanel.add(successLabel);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        IDField.setText("");
        workField.setText("");
        familyField.setText("");
    }

    private void InitialiseformField() {
        nameField=new JTextField();
        familyField=new JTextField();
        IDField=new JTextField();
        emailField=new JTextField();
        workField=new JTextField();
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}