package UI;
import doctors.doctorForm;
import doctors.doctorView;
import patients.patientsView;
import patients.searchPatient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class MenuBar extends JMenuBar{
    JMenu patients,view,doctor;
    JMenuItem patientItem,viewItem,doctorItem;
    JPopupMenu pop;

    public MenuBar(){
        patients=new JMenu("Patients");
        doctor=new JMenu("Doctors");
        view=new JMenu("View");


        //pateints menu items

        JMenuItem patientItem2=new JMenuItem("Search patient list");
        patientItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    searchPatient remove=new searchPatient();
                    System.out.println("TEST");

                }catch(SQLException | URISyntaxException e){
                    e.printStackTrace();
                }
            }
        });
        add(patientItem2);
        patients.add(patientItem2);

        //dr menu items
        doctorItem=new JMenuItem("Add Doctor");
        doctorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    doctorForm dr = new doctorForm();
                } catch (SQLException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        add(doctor);
        doctor.add(doctorItem);


        viewItem=new JMenuItem("All Patients");
        add(view);
        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    patientsView pview= new patientsView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        view.add(viewItem);

        viewItem=new JMenuItem("All Doctors");
        add(view);
        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    doctorView drview= new doctorView();
                } catch (SQLException | IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        view.add(viewItem);
    }



}
