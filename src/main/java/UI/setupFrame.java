package UI;

import javax.swing.*;
import java.awt.*;

public class setupFrame {
    private JDialog frame;

    public void setFrame(){
        frame = new JDialog();
        frame.revalidate();
        frame.setSize(500, 600);
        frame.setJMenuBar(new MenuBar());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(2);
    }

    public JDialog getFrame(){
        return frame;
    }

    public void add(JPanel mainPanel){
        frame.add(mainPanel);
    }

    public void setTitle(String title){
        frame.setTitle(title);
    }




}
