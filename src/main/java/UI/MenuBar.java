package UI;
import doctors.printDoctors;
import patients.printPatients;
import patients.removePatient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        patientItem=new JMenuItem("Add Patient");

        patientItem.addActionListener(actionEvent -> {
            try {
                patientForm form=new patientForm();

            } catch (SQLException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        patients.add(patientItem);
        add(patients);


        JMenuItem patientItem2=new JMenuItem("Discharge patient");
        patientItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    removePatient remove=new removePatient();
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

        doctorItem=new JMenuItem("Search Doctor");
        add(doctor);
        doctor.add(doctorItem);


        //view items
        viewItem=new JMenuItem("Current Admitted Patients");
        add(view);
        view.add(viewItem);

        viewItem=new JMenuItem("Current Available Doctors");
        add(view);
        view.add(viewItem);

        viewItem=new JMenuItem("All Patients");
        add(view);
        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                printPatients print=new printPatients();
                try {
                    print.printP();
                } catch (SQLException | URISyntaxException e) {
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
                printDoctors printdr=new printDoctors();
                try{
                    printdr.printD();
                } catch (SQLException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        view.add(viewItem);
    }



}
