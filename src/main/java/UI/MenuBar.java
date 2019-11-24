package UI;

import patients.printPatients;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        patientItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    patientForm form=new patientForm();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        add(patients);
        patients.add(patientItem);

        //dr menu items
        doctorItem=new JMenuItem("Add Doctor");
        doctorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    doctorForm dr = new doctorForm();
                } catch (SQLException e) {
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        view.add(viewItem);

        viewItem=new JMenuItem("All Doctors");
        add(view);
        view.add(viewItem);
    }



}
