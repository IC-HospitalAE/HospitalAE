package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class mainPage {
    private JPanel mapPanel=new JPanel(new GridLayout(10,10)),buttonPanel=new JPanel(new GridLayout(1,2));
    private JPanel mainPanel=new JPanel(new GridLayout(2,1));
    private JPanel addPatientsPanel=new JPanel();

    private JLabel tempLabel=new JLabel("Map will go here");

    private JButton addP,removeP;

    public mainPage(){

        addP=new JButton("Add Patient");
        removeP =new JButton("Discharge Patient");

        addP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    patientForm form=new patientForm();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //add buttons to button panel
        buttonPanel.add(addP);
        buttonPanel.add(removeP);

        //add button panel to add patients panel
        addPatientsPanel.add(buttonPanel);

        //map panel
        mapPanel.setBackground(new Color(116, 185, 255));
        mapPanel.add(tempLabel);

        //add the map n aptient panel to main panel
        mainPanel.add(addPatientsPanel);
        mainPanel.add(mapPanel);

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
