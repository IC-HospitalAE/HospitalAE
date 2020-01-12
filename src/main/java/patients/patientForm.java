package patients;

import JSON.Patient;
import JSON.patientArray;
import UI.setupFrame;
import bed.assignBed;
import database_conn.connectDatabase;
import doctors.makePostRequest;
import doctors.patient_to_doctor;
import doctors.sendEmail;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

    //variables
    private String name;
    private String age;
    private String ID;
    private String notes;
    private String phonenumber;
    private String familyname;
    private String bedEntered;

//    ArrayList<String> doctorsAvailable= new gettingAvailableDrs().getAvailableDr();
    ArrayList<String>doctorAvailable=new ArrayList<>();


    public patientForm(String bed_in) throws SQLException, URISyntaxException, IOException {

        String drassigned=null;
        String drfist=null;
        String drlast=null;

        //setting frame
        frame.setFrame();
        frame.setTitle("Add patients");
        frame.getFrame();

        //get connection to db
        connectDatabase conn= new connectDatabase();
        conn.getConnection();

        initialiseFormField(bed_in);

        //initalise the label not to print wout the button pressed
        succcessLabel.setVisible(false);

        p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                name= nameField.getText();
                familyname=familyField.getText();
                age=ageField.getText();
                ID=IDField.getText();
                notes=notesField.getText();
                phonenumber=phoneField.getText();
                bedEntered=bed_in;

                //get date and time from system
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                LocalDateTime now = LocalDateTime.now();

                //here goes the JSON conversion and sending to servlet
//                sendingJSON json= new sendingJSON();
//                json.PostRequest(patient);

                //add patient to LOCAL postgres db
                try{
                    if((bed.isBedEmpty(bedEntered) == true)&&(!name.isEmpty())&&(!familyname.isEmpty())&&(!ID.isEmpty())&&(!phonenumber.isEmpty())){

                        //this sends the entered info to the patient class
                        Patient patient=new Patient(name,familyname,ID,age,notes,dtf.format(now),phonenumber,bedEntered);
                        patientArray pArray=new patientArray();
                        pArray.addPatient(patient); //add to array

                        Statement s= conn.createStatement();
                        String sql = "INSERT INTO patients (firstname, lastname, phonenumber, identitynumber, age, notes,admit_status,bednumber,time_date,doctor_incharge) values ('"+name+"','"+familyname+"','"+phonenumber+"','"+ID+"','"+age+"','"+notes+"',true,'"+bedEntered+"','"+dtf.format(now)+"','');";
                        s.execute(sql);
                        succcessLabel.setText("Successfully added patient!");
                        succcessLabel.setVisible(true);
                        bed.setBedUnavailable(bedEntered);
                        clearFields();
                    }else{
                        succcessLabel.setText("Some inputs are left empty!");
                        succcessLabel.setVisible(true);
                    }
                }catch(Exception e){
                    succcessLabel.setText("Bed is occupied!");
                    succcessLabel.setVisible(true);
                }

                try{
                    String timeDate=dtf.format(now);
                    String DrFirstNameOnly = null;
                    String DrLastNameOnly=null;

                    if(rand()>=0){
                        String drassigned=doctorAvailable.get(rand());
                        String[] splitname=drassigned.split(" ");
                        DrFirstNameOnly=splitname[0];
                        DrLastNameOnly=splitname[1];

                        String sql2="UPDATE beds SET check_in_time='"+timeDate+"' WHERE bed_id='"+bedEntered+"' ";
                        String sql3="UPDATE beds SET patient_id='"+name+"' where bed_id='"+bedEntered+"'";
                        String sql4="UPDATE beds SET doctor_id='"+drassigned+"' WHERE bed_id='"+bedEntered+"'    ;";
                        String sql5="UPDATE doctors set num_patients=num_patients+1 WHERE firstname = '"+DrFirstNameOnly+"' AND lastname='"+DrLastNameOnly+"';";

                        patient_to_doctor patientToDoctor=new patient_to_doctor(name,familyname,DrFirstNameOnly,DrLastNameOnly);
                        makePostRequest sendToWebsite=new makePostRequest(patientToDoctor);

                        Statement s2=conn.createStatement();
                        s2.execute(sql2);
                        s2.execute(sql3);
                        s2.execute(sql4);
                        s2.execute(sql5);
                    }else {
                        succcessLabel.setText("Not enough doctors! Emails have been sent.");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        succcessLabel.setVisible(false);

        //add all labels and text fields to main panel
        styleLabels();
        addToPanel();

        // add buttons to button panel
        p_btn.setFont(new Font("Raleway Light",Font.PLAIN,20));
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

    private int rand() throws SQLException {

        int min=0; //lowest index

        connectDatabase connect=new connectDatabase();
        Statement st=connect.createStatement();

        String sql="SELECT firstname,lastname from doctors where num_patients<4 AND availability=true;";
        ResultSet rset=st.executeQuery(sql);

        while(rset.next()){
            String DRfirstname=rset.getString("firstname");
            String DRlastname=rset.getString("lastname");
            doctorAvailable.add(DRfirstname+" "+DRlastname);
        }

        int max=doctorAvailable.size(); //highest index

        if(max==0){
            sendEmail sendmail= new sendEmail();
            return -1;
        }else{

        }

        int rand = ThreadLocalRandom.current().nextInt(min, max);

        connect.close();
        return rand;
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
    private void styleLabels(){

        int fontSize=22;
        nameLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        familyLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        ageLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        IDLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        phoneLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        notesLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        bedId.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));

        succcessLabel.setFont(new Font("Raleway Light", Font.PLAIN, 27));
        succcessLabel.setForeground(Color.RED);

        bedAvailableLabel.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));

        nameField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        familyField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        ageField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        notesField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        phoneField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        bedField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));
        IDField.setFont(new Font("Raleway Light", Font.PLAIN, fontSize));

        Color background =new Color(44, 44, 88);
        mainPanel.setBackground(background);

        nameLabel.setForeground(Color.white);
        familyLabel.setForeground(Color.white);
        ageLabel.setForeground(Color.white);
        IDLabel.setForeground(Color.white);
        phoneLabel.setForeground(Color.white);
        notesLabel.setForeground(Color.white);
        bedId.setForeground(Color.white);
        bedAvailableLabel.setForeground(Color.white);

        buttonPanel.setBackground(background);
        labelPanels.setBackground(background);
        succcessLabel.setBackground(background);
        bedsPanel.setBackground(background);

        mainPanel.setBorder(new EmptyBorder(0,10,0,10));
    }
    private void initialiseFormField(String bed_in) {
        nameField=new JTextField();
        familyField=new JTextField();
        ageField=new JTextField();
        IDField=new JTextField();
        notesField=new JTextField();
        phoneField=new JTextField();
        bedField=new JTextField(bed_in);

    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
}
