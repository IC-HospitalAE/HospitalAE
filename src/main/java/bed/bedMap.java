package bed;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database_conn.connectDatabase;
public class bedMap {

    private JPanel mapView=new JPanel(new GridLayout(2,5));

    private JPanel bed1=new JPanel(),bed2,bed3,bed4,bed5,bed6,bed7,bed8,bed9,bed10;

    private connectDatabase conn = new connectDatabase();

    private assignBed beds=new assignBed();

    public bedMap() throws SQLException, URISyntaxException {

        mapView.add(bedSetup(bed1,"1"));
        mapView.add(bedSetup(bed2,"2"));
        mapView.add(bedSetup(bed3,"3"));
        mapView.add(bedSetup(bed4,"4"));
        mapView.add(bedSetup(bed5,"5"));
        mapView.add(bedSetup(bed6,"6"));
        mapView.add(bedSetup(bed1,"7"));
        mapView.add(bedSetup(bed2,"8"));
        mapView.add(bedSetup(bed3,"9"));
        mapView.add(bedSetup(bed4,"10"));

    }

    public JPanel bedSetup(JPanel bed_in,String bed_num) throws SQLException {

        bed_in=new JPanel(new GridLayout(3,1));
        JLabel patientLabel=new JLabel();
        JLabel doctorLabel;
        final String bed_id=bed_num;

        //set up the labels
        JLabel bedLabel=new JLabel("Bed #"+bed_id );
        patientLabel = setPatientName(bed_id,patientLabel);
        doctorLabel=new JLabel("Dr name");

        //centering the labels on the panel
        bedLabel.setHorizontalAlignment(JLabel.CENTER);
        patientLabel.setHorizontalAlignment(JLabel.CENTER);
        doctorLabel.setHorizontalAlignment(JLabel.CENTER);

        //setting border
        setBorder(bed_in);

        bed_in.add(bedLabel);
        bed_in.add(patientLabel);
        bed_in.add(doctorLabel);
        System.out.println(getPatientName(bed_id));

        return bed_in;


    }

    private JLabel setPatientName(String bed_id,JLabel patientLabel) throws SQLException {

        if (beds.isBedEmpty(bed_id) == true) {
            System.out.println("free");
            patientLabel = new JLabel("Bed is free!");
        } else {
            System.out.println("not");
            patientLabel = new JLabel(getPatientName(bed_id));
        }
        return patientLabel;
    }

    private void resetName(String bed_in){

    }

    public String getPatientName(String bed_id) throws SQLException {
        String patientname = new String();

        Statement s=conn.createStatement();
        String sql="SELECT givenname from patients where bednumber='"+bed_id+"'  ";
        ResultSet rset= s.executeQuery(sql);

        while(rset.next()){
            patientname=rset.getString("givenname");
        }

        return patientname;
    }

    private String getDoctorName(String bed_id) throws SQLException{
        String doctor = new String();

        return doctor;
    }

    public JPanel getBedsPanel(){

        return mapView;
    }

    public void setBorder(JPanel panel_in){
        panel_in.setBorder(new LineBorder(Color.BLACK,1));
    }
}

