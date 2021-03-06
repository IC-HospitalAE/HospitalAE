package doctors;

import UI.countRowsRequired;
import UI.setupFrame;
import bed.assignBed;
import database_conn.connectDatabase;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class doctorView {

    private JPanel mainPanel = new JPanel();
    private JPanel doctorPanel = new JPanel(new GridLayout(5, 2));
    private JPanel cards;

    private JLabel firstname, lastname, id, status, email, workload,shift;

    private setupFrame frame = new setupFrame();

    private connectDatabase conn = new connectDatabase();

    public doctorView() throws SQLException, IOException, URISyntaxException {
        //setup Frame
        frame.setFrame();
        frame.setTitle("All doctors");
        frame.getFrame();
        updateLocalDoctorDB pp=new updateLocalDoctorDB();
        getAllDoctors();
    }

    private void getAllDoctors() throws SQLException, URISyntaxException, IOException {

        assignBed aa=new assignBed();

        Statement s = conn.createStatement();
        String sql = "SELECT firstname,lastname,identitynumber,email,workload,availability,shift from doctors WHERE id>0 ORDER BY availability;" ;
        ResultSet rset = s.executeQuery(sql);

        int numberRows = new countRowsRequired().getrowCount();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(10);

        while (rset.next()) {
            firstname = new JLabel("First name: "+ "Dr " + rset.getString("firstname"));
            lastname = new JLabel("Last name: " + rset.getString("lastname"));
            id = new JLabel("Identity number: " + rset.getString("identitynumber"));
            email = new JLabel("Email: " + (rset.getString("email")));
            workload = new JLabel("Workload: " + rset.getString("workload")+" hours");
            shift= new JLabel("Next shift: " +rset.getString("shift"));

            if (!rset.getBoolean("availability")) {
                boolean admitted;
                status = new JLabel("Status: Not on duty");
            } else {
                status = new JLabel("Status: On duty");
            }

            cards = new JPanel(new GridLayout(7, 1));
            mainPanel.setLayout(new GridLayout(1, 1));

            cards.add(firstname);
            cards.add(lastname);
            cards.add(id);
            cards.add(email);
            cards.add(workload);
            cards.add(status);
            cards.add(shift);

            pageStyle();
            cardStyle(cards, rset.getBoolean("availability"));

            doctorPanel.add(cards);
            mainPanel.add(doctorPanel);

            frame.add(mainPanel);
        }

        JScrollPane scroll = new JScrollPane(doctorPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getHorizontalScrollBar().setUnitIncrement(20);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        mainPanel.add(scroll);
    }

    private void cardStyle(JPanel card_in, boolean admit) {

        Color notduty = new Color(210, 210, 210, 255);
        Color duty = new Color(253, 253, 253);
        Color border = new Color(44, 44, 88);

        firstname.setHorizontalAlignment(JLabel.LEFT);
        lastname.setHorizontalAlignment(JLabel.LEFT);
        id.setHorizontalAlignment(JLabel.LEFT);
        email.setHorizontalAlignment(JLabel.LEFT);
        workload.setHorizontalAlignment(JLabel.LEFT);
        status.setHorizontalAlignment(JLabel.LEFT);
        status.setHorizontalAlignment(JLabel.LEFT);

        firstname.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        lastname.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        id.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        email.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        workload.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        status.setFont(new Font("Raleway Light", Font.PLAIN, 15));
        shift.setFont(new Font("Raleway Light", Font.PLAIN, 15));

        card_in.setPreferredSize(new Dimension(100, 250));

        CompoundBorder cardBorder = new CompoundBorder(new LineBorder(border, 3), new EmptyBorder(10, 20, 10, 10));
        card_in.setBorder(cardBorder);

        if (!admit) {
            card_in.setBackground(notduty);
        } else {
            card_in.setBackground(duty);
        }
    }

    private void pageStyle() {
        Color background = new Color(44, 44, 88);

        doctorPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        doctorPanel.setBackground(background);
        frame.setBackground(background);
        mainPanel.setBackground(background);

    }
}