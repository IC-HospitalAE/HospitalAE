package database_conn;

import UI.setupFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class databaseLoginDialog{

    private setupFrame frame =new setupFrame();
    private JPanel mainPanel=new JPanel(new GridLayout(3,1));

    private JTextField usernameField=new JTextField();
    private JTextField passwordField=new JTextField();
    private JLabel usernameLabel= new JLabel("Username: ");
    private JLabel passwordLabel=new JLabel("Password: ");
    private JLabel errorLabel= new JLabel("Error! Please enter correct username or password");


    private JButton submit =new JButton("Enter");

    private String password;
    private String username;

    public databaseLoginDialog(){
        frame.setFrame();
        frame.setSize(450,650);
        frame.setCenter();
        frame.getFrame();



        errorLabel.setVisible(false);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username=usernameField.getText();
                password=passwordField.getText();
                System.out.println(username);
                System.out.println(password);

                try {
                    initialiseDB initialise=new initialiseDB(username,password);
                    frame.closeFrame();

                } catch (SQLException | IOException | URISyntaxException ex) {
                    errorLabel.setVisible(true);
                    ex.printStackTrace(); //debug
                }

            }
        });

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(errorLabel);
        mainPanel.add(submit);
        frame.add(mainPanel);
    }
}

