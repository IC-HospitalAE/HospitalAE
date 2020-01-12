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
    private JPanel mainPanel=new JPanel();
    private JPanel titlePanel=new JPanel();
    private JPanel inputPanel=new JPanel(new GridLayout(4,1));

    private JTextField usernameField=new JTextField();
    private JTextField passwordField=new JTextField();
    private JLabel usernameLabel= new JLabel("Username: ");
    private JLabel passwordLabel=new JLabel("Password: ");
    private JLabel errorLabel= new JLabel("Error! Please enter correct username or password");
    private JLabel title=new JLabel("Enter Postgres Username and Password ");

    private JButton submit =new JButton("Enter");

    private static String password;
    private static String username;

    public databaseLoginDialog(){

    }

    public void setupDialog(){
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
                    String err=ex.getMessage(); //debug
                    errorLabel.setText(err);
                    errorLabel.setVisible(true);
                    System.out.println(err);
                }

            }
        });

        stylePage();

        titlePanel.add(title);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(errorLabel);
        inputPanel.add(submit);

        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        frame.add(mainPanel);
    }

    protected static String getUser(){
        return username;
    }
    protected String getPass(){
        return password;
    }

    private void stylePage(){
        Color background = new Color(44, 44, 88);
        Color font = new Color(253, 253, 253);
        Color red=new Color(181, 26, 19, 255);

        titlePanel.setBackground(background);
        inputPanel.setBackground(background);
        mainPanel.setBackground(background);

        title.setForeground(font);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(550,200));
        title.setFont(new Font("Raleway Light", Font.PLAIN, 27));

        usernameLabel.setForeground(font);
        passwordLabel.setForeground(font);
        usernameLabel.setFont(new Font("Raleway Light", Font.PLAIN, 27));
        passwordLabel.setFont(new Font("Raleway Light", Font.PLAIN, 27));

        usernameField.setFont(new Font("Raleway Light", Font.PLAIN, 25));
        passwordField.setFont(new Font("Raleway Light", Font.PLAIN, 25));
        errorLabel.setForeground(red);

    }
}

