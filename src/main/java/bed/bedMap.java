package bed;

import database_conn.connectDatabase;
import patients.dischargePatient;
import patients.patientForm;

import javax.swing.*;
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

public class bedMap {

    private JPanel mapView=new JPanel(new GridLayout(2,5));
    private JPanel bed1=new JPanel(),bed2=new JPanel(),bed3,bed4,bed5,bed6,bed7,bed8,bed9,bed10;

    private assignBed beds=new assignBed();
    private dischargePatient dischargepatient = new dischargePatient();

    String lastname;

    public bedMap() throws SQLException, URISyntaxException, IOException {

        mapView.add(bedSetup("1"));
        mapView.add(bedSetup("2"));
        mapView.add(bedSetup("3"));
        mapView.add(bedSetup("4"));
        mapView.add(bedSetup("5"));
        mapView.add(bedSetup("6"));
        mapView.add(bedSetup("7"));
        mapView.add(bedSetup("8"));
        mapView.add(bedSetup("9"));
        mapView.add(bedSetup("10"));

    }

    public JPanel bedSetup(String bed_num) throws SQLException, IOException, URISyntaxException {

        JPanel bed_in = new JPanel(new GridLayout(5, 1));
        JLabel patientLabel;
        JLabel doctorLabel;

        JButton discharge= new JButton("Discharge");
        JButton admit=new JButton("Admit");

        //set up the labels
        JLabel bedLabel=new JLabel("Bed #"+bed_num );


        patientLabel = setPatientName(bed_num);
        //gets the dr names for each bed
        doctorLabel=setDoctorName(bed_num);


        //centering the labels on the panel
        bedLabel.setHorizontalAlignment(JLabel.CENTER);
        patientLabel.setHorizontalAlignment(JLabel.CENTER);
        doctorLabel.setHorizontalAlignment(JLabel.CENTER);

        //setting border
        setBorder(bed_in);

        //style panel
        stylePanel(bedLabel,patientLabel,doctorLabel);

        //add to panel
        bed_in.add(bedLabel);
        bed_in.add(patientLabel);
        bed_in.add(doctorLabel);


        //discharge button
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //get time and date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String timeDate=dtf.format(now);

                try {
                    String patientName=getPatientName(bed_num);
                    String drname=getDoctorName(bed_num);
                    dischargepatient.discharge(patientName,bed_num,timeDate,drname);
                } catch (SQLException | IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
                bed_in.revalidate();
            }
        });

        //admit button
        admit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    patientForm addPatient=new patientForm(bed_num);

                } catch (SQLException | URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //if bed is empty it'll have different color
        //if bed not empty, add discharge button
        if(beds.isBedEmpty(bed_num)){
            bed_in.setBackground(new Color(205, 205, 205, 255));
            bed_in.add(admit);
        }else{
            bed_in.setBackground(new Color(253,253,253));
            bed_in.add(discharge);
        }

        bed_in.revalidate();
        return bed_in;
    }

    private JLabel setPatientName(String bed_id) throws SQLException, IOException, URISyntaxException {
        JLabel patientLabel=new JLabel();

        if (beds.isBedEmpty(bed_id) == true) {
            patientLabel.setText("Bed is free!");
        }else {
            patientLabel.setText(getPatientName(bed_id)+" "+getPatientLastName(bed_id));
        }
        return patientLabel;
    }
    private JLabel setDoctorName(String bed_id) throws SQLException, IOException, URISyntaxException {
        JLabel doctorLabel=new JLabel();

        if (beds.isBedEmpty(bed_id) == true) {
            doctorLabel.setText("");
        }else {
            doctorLabel.setText(getDoctorName(bed_id));
        }
        return doctorLabel;
    }

    private String getPatientName(String bed_id) throws SQLException, IOException, URISyntaxException {
        connectDatabase conn = new connectDatabase();
        String patientname = new String();
        conn.getConnection();

        Statement s=conn.createStatement();
        String sql="SELECT firstname from patients where bednumber='"+bed_id+"'  ";
        ResultSet rset= s.executeQuery(sql);

        while(rset.next()){
            patientname=rset.getString("firstname");
        }
        conn.close();
        return patientname;

    }

    private String getPatientLastName(String bed_id) throws SQLException, IOException, URISyntaxException {
        connectDatabase conn = new connectDatabase();

        conn.getConnection();
        Statement s=conn.createStatement();
        String sql="SELECT lastname from patients where bednumber='"+bed_id+"'  ";
        ResultSet rset= s.executeQuery(sql);

        while(rset.next()){
            lastname=rset.getString("lastname");
        }

        conn.close();
        return lastname;
    }

    private String getDoctorName(String bed_id) throws IOException, SQLException, URISyntaxException {
        String doctor = new String();

        connectDatabase conn = new connectDatabase();
        conn.getConnection();

        Statement s=conn.createStatement();
        String sql="SELECT doctor_id FROM beds where bed_id='"+bed_id+"'";
        ResultSet rset=s.executeQuery(sql);

        while (rset.next()){
            doctor="Dr "+rset.getString("doctor_id");
        }
        conn.close();
        return doctor;
    }

    public JPanel getBedsPanel(){
        return mapView;
    } //send this to mainUI class

    private void setBorder(JPanel panel_in){
        panel_in.setBorder(new LineBorder(Color.BLACK,1));
        //panel_in.setBackground(new Color(253,253,253));
    }

    private void stylePanel(JLabel bed, JLabel patients, JLabel doctors){
        bed.setFont(new Font("Raleway Light", Font.PLAIN, 17));
        patients.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        doctors.setFont(new Font("Raleway Light", Font.PLAIN, 15));
    }
}

