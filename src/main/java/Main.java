import UI.MenuBar;
import UI.mainPage;
import UI.patientForm;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.sql.*;

public class Main {
    static GraphicsConfiguration gc;

    public static void main(String args[]) throws SQLException, URISyntaxException {
        JFrame mainFrame;
        JPanel p;
        mainFrame = new JFrame("App");
        mainFrame.setSize(500, 600);
        mainFrame.setJMenuBar(new MenuBar());
        mainPage main=new mainPage();
        p=main.getMainPanel();
        mainFrame.getContentPane().add(p);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(3);
    }


}
