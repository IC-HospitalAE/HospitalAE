package UI;
import patients.Patients;
import patients.PatientsDB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class patientForm{
    private PatientsDB p_db=new PatientsDB();
    private Patients patients;

    private JFrame frame;

    //jpanels to add components
    private JPanel mainPanel= new JPanel(new GridLayout(7,2));
    private JPanel buttonPanel=new JPanel(new GridLayout(1,10));

    //buttons
    private JButton p_btn = new JButton("Add new patient");

    //text fields
    private JTextField nameField,familyField,ageField,notesField,IDField,phoneField;

    //initialise labels
    private JLabel nameLabel=new JLabel("First name:");
    private JLabel familyLabel=new JLabel("Family name:");
    private JLabel ageLabel=new JLabel("Age:");
    private JLabel notesLabel=new JLabel("Notes:");
    private JLabel IDLabel=new JLabel("ID number:");
    private JLabel phoneLabel=new JLabel("Phone number:");
    private final JLabel succcessLabel= new JLabel("Patient added successfully!"); //prints only if patient is added

    //variables
    private String name=new String();
    private String age=new String();
    private String ID=new String();
    private String notes=new String();
    private String phonenumber=new String();
    private String familyname=new String();


    public patientForm() throws SQLException {

        setupFrame();
        String dbUrl= "jdbc:postgresql://localhost:5432/project";
        Connection conn= DriverManager.getConnection(dbUrl);
        formField();

        //initalise the label not to print wout the button pressed
        succcessLabel.setVisible(false);

        p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                name=nameField.getText();
                familyname=familyField.getText();
                age=ageField.getText();
                ID=IDField.getText();
                notes=notesField.getText();
                phonenumber=phoneField.getText();

                patients=new Patients(name,age,notes,ID,phonenumber);

                //add patient to array
                p_db.addPatient(patients);

                //add patient to postgres db
                try {
                    Statement s=conn.createStatement();
                    String sqlStr = "INSERT INTO patients (givenname, familyname, phonenumber, identitynumber, age, notes) values ('"+name+"','"+familyname+"','"+phonenumber+"','"+ID+"','"+age+"','"+notes+"');";
                    s.execute (sqlStr);
                }
                catch (Exception e){
                }

                //clears the text field after button pressed
                clearFields();

                //prints the label when button pressed
                succcessLabel.setVisible(true);
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
        mainPanel.revalidate();

    }


    private void setupFrame(){
        frame = new JFrame("Add Patient");
        frame.setSize(500, 600);
        frame.setJMenuBar(new MenuBar());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(2);
    }
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        IDField.setText("");
        notesField.setText("");
        phoneField.setText("");
        familyField.setText("");
    }
    private void addToPanel() {
        doctorForm.setupLabels(mainPanel, nameLabel, nameField, familyLabel, familyField, ageLabel, ageField, IDLabel, IDField, notesLabel, notesField, phoneLabel);
        mainPanel.add(phoneField);
        mainPanel.add(succcessLabel);
    }
    private void formField() {
        nameField=new JTextField();
        familyField=new JTextField();
        ageField=new JTextField();
        IDField=new JTextField();
        notesField=new JTextField();
        phoneField=new JTextField();

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
