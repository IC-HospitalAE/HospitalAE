package UI;

import doctors.Doctor;
import doctors.doctor_database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class doctorForm {
    private doctor_database dr_db=new doctor_database();
    private Doctor doctor;

    private JFrame frame;

    //jpanels to add components
    private JPanel mainPanel= new JPanel(new GridLayout(7,2));
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
    private JLabel workLabel=new JLabel("Workload (hours):");
    private final JLabel successLabel = new JLabel("Doctor added successfully!"); //prints only if patient is added

    //variables
    private String given_name=new String();
    private String familyname=new String();
    private String ID=new String();
    private String email=new String();
    private String workhrs=new String();

    public doctorForm() throws SQLException {

        setupFrame();

        String dbUrl= "jdbc:postgresql://localhost:5432/project";
        Connection conn= DriverManager.getConnection(dbUrl);
        formField();

        //initalise the label not to print wout the button pressed
        successLabel.setVisible(false);

        p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                given_name=nameField.getText();
                familyname=familyField.getText();
                ID=IDField.getText();
                email=emailField.getText();
                workhrs=workField.getText();

                doctor=new Doctor(given_name,familyname,ID,workhrs);

                //add dr to array
                dr_db.addDoctor(doctor);

                //add dr to postgres db
                try {
                    Statement s=conn.createStatement();
                    String sqlStr = "INSERT INTO doctors (firstname, lastname,identitynumber, email, workload) values ('"+given_name+"','"+familyname+"','"+ID+"','"+email+"','"+workhrs+"');";
                    s.execute (sqlStr);
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
        addToPanel();

        // add buttons to button panel
        buttonPanel.add(p_btn);
        buttonPanel.setPreferredSize(new Dimension(50,50));

        //add btn to main panel
        mainPanel.add(buttonPanel,BorderLayout.CENTER);

        mainPanel.revalidate();
        frame.add(mainPanel);

    }


    private void setupFrame(){
        frame = new JFrame("Add Doctor");
        frame.setSize(500, 600);
        frame.setJMenuBar(new MenuBar());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(2);
    }
    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        IDField.setText("");
        workField.setText("");
        familyField.setText("");
    }
    private void addToPanel() {
        setupLabels(mainPanel, nameLabel, nameField, familyLabel, familyField, emailLabel, emailField, IDLabel, IDField, workLabel, workField, successLabel);
    }

    static void setupLabels(JPanel mainPanel, JLabel nameLabel, JTextField nameField, JLabel familyLabel, JTextField familyField, JLabel emailLabel, JTextField emailField, JLabel idLabel, JTextField idField, JLabel workLabel, JTextField workField, JLabel successLabel) {
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(familyLabel);
        mainPanel.add(familyField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(workLabel);
        mainPanel.add(workField);
        mainPanel.add(successLabel);
    }

    private void formField() {
        nameField=new JTextField();
        nameField.setColumns(128);
        familyField=new JTextField();
        IDField=new JTextField();
        emailField=new JTextField();
        workField=new JTextField();

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}