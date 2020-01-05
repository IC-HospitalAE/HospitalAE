import UI.MenuBar;
import UI.mainPage;
import database_conn.initialiseDB;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException, URISyntaxException, IOException {

        initialiseDB db=new initialiseDB(); //initlaise the database
        System.out.println("Success!!!");
        JFrame mainFrame=new JFrame("Hospital");
        JPanel p;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.setJMenuBar(new MenuBar());
        mainPage main=new mainPage();
        p=main.getMainPanel();
        mainFrame.getContentPane().add(p);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(3);

    }
}

