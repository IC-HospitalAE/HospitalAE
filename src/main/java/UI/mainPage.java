package UI;
import bed.bedMap;
import doctors.printDoctors;
import patients.printPatients;
import patients.removePatient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class mainPage {

    private JPanel mapPanel=new JPanel(new GridLayout(10,10));
    private JPanel buttonPanel=new JPanel(new GridLayout(5,1));
    private JPanel mainPanel=new JPanel(new GridLayout(2,1));
    private JPanel addPatientsPanel=new JPanel();

    private JLabel tempLabel=new JLabel("Map will go here");

    private JButton addP,removeP,viewP,viewDr,refreshBtn;

    private bedMap bedmap = new bedMap();

    public mainPage() throws SQLException, URISyntaxException {

        addP=new JButton("Add Patient");
        removeP =new JButton("Discharge Patient");
        viewP=new JButton("View all patients");
        viewDr= new JButton("View all doctors");
        refreshBtn=new JButton("Refresh Page");

        addP.addActionListener(actionEvent -> {
            try {
                patientForm form=new patientForm();
            } catch (SQLException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        removeP.addActionListener(actionEvent -> {
            try {
                removePatient remove=new removePatient();
            } catch (SQLException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        viewP.addActionListener(actionEvent -> {
            printPatients view = new printPatients();
            try {
                view.printP();
            } catch (SQLException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        viewDr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                printDoctors viewdr= new printDoctors();
                try {
                    viewdr.printD();
                } catch (SQLException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bedmap.getBedsPanel().revalidate();
            }
        });

        //add buttons to button panel
        buttonPanel.add(addP);
        buttonPanel.add(removeP);
        buttonPanel.add(viewP);
        buttonPanel.add(viewDr);
        buttonPanel.add(refreshBtn);

        //add button panel to add patients panel
        addPatientsPanel.add(buttonPanel);

        //add the map n patient panel to main panel
        mainPanel.add(addPatientsPanel);
        mainPanel.add(bedmap.getBedsPanel());
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
