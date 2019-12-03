package UI;
import bed.assignBed;
import database_conn.connectDatabase;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Statement;

public class patientForm{

    //class initialisation
    private setupFrame frame =new setupFrame();
    private assignBed bed= new assignBed();


    //jpanels to add components
    private JPanel mainPanel= new JPanel(new GridLayout(9,2));
    private JPanel buttonPanel=new JPanel(new GridLayout(1,1));
    private JPanel bedsPanel= new JPanel(new GridLayout(1,2));
    private JPanel labelPanels=new JPanel(new GridLayout(2,1));

    //buttons
    private JButton p_btn = new JButton("Add new patient");

    //text fields
    private JTextField nameField,familyField,ageField,notesField,IDField,phoneField,bedField;

    //initialise labels
    private JLabel nameLabel=new JLabel("First name:");
    private JLabel familyLabel=new JLabel("Family name:");
    private JLabel ageLabel=new JLabel("Age:");
    private JLabel notesLabel=new JLabel("Notes:");
    private JLabel IDLabel=new JLabel("ID number:");
    private JLabel phoneLabel=new JLabel("Phone number:");
    private JLabel bedId=new JLabel("Bed number:");

    private final JLabel succcessLabel= new JLabel("Patient added successfully!"); //prints only if patient is added
    private final JLabel bedAvailableLabel= new JLabel("Available beds:");
    private final JLabel bedFullLabel= new JLabel("Bed entered is occupied!");


    //variables
    private String name=new String();
    private String age=new String();
    private String ID=new String();
    private String notes=new String();
    private String phonenumber=new String();
    private String familyname=new String();
    private String bednumber=new String();

    public patientForm() throws SQLException, URISyntaxException {

        //setting frame
        frame.setFrame();
        frame.setTitle("Add patients");
        frame.getFrame();

        //get connection to db
        connectDatabase conn= new connectDatabase();
        conn.getConnection();

        initialiseFormField();

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
                bednumber=bedField.getText();
                String bedEntered=bedField.getText();

                //add patient to postgres db
                try{
                    if(bed.isBedEmpty(bedEntered) == true){
                        Statement s= conn.createStatement();
                        String sql = "INSERT INTO patients (givenname, familyname, phonenumber, identitynumber, age, notes,admit_status,bednumber) values ('"+name+"','"+familyname+"','"+phonenumber+"','"+ID+"','"+age+"','"+notes+"',true,'"+bedEntered+"' );";
                        s.execute(sql);
                        succcessLabel.setText("Successfully added patient!");
                        succcessLabel.setVisible(true);
                        bed.setBedUnavailable(bedEntered);
                        clearFields();
                    }else{
                        succcessLabel.setText("Bed is occupied");
                        succcessLabel.setVisible(true);

                        //succcessLabel.setText("kk");
                        succcessLabel.revalidate();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        succcessLabel.setVisible(false);

        //add all labels and text fields to main panel
        addToPanel();

        // add buttons to button panel
        buttonPanel.add(p_btn);
        buttonPanel.setPreferredSize(new Dimension(50,50));

        //panel to show the beds available
        bedsPanel.setBorder(new LineBorder(Color.BLACK,1));
        bedsPanel.add(bed.getAvailableBeds());


        //add bedpanel to mainpanel
        labelPanels.add(succcessLabel);

        mainPanel.add(labelPanels);
        mainPanel.add(buttonPanel,BorderLayout.CENTER);
        mainPanel.add(bedAvailableLabel);
        mainPanel.add(bedsPanel);
        mainPanel.revalidate();
        frame.add(mainPanel);
    }
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        IDField.setText("");
        notesField.setText("");
        phoneField.setText("");
        familyField.setText("");
        bedField.setText("");
    }
    private void addToPanel() {
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(familyLabel);
        mainPanel.add(familyField);
        mainPanel.add(ageLabel);
        mainPanel.add(ageField);
        mainPanel.add(IDLabel);
        mainPanel.add(IDField);
        mainPanel.add(phoneLabel);
        mainPanel.add(phoneField);
        mainPanel.add(notesLabel);
        mainPanel.add(notesField);
        mainPanel.add(bedId);
        mainPanel.add(bedField);
    }

    private void initialiseFormField() {
        nameField=new JTextField();
        familyField=new JTextField();
        ageField=new JTextField();
        IDField=new JTextField();
        notesField=new JTextField();
        phoneField=new JTextField();
        bedField=new JTextField();
    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
}
