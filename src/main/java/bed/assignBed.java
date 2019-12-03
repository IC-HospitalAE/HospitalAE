package bed;

import UI.countRowsRequired;
import database_conn.connectDatabase;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class assignBed {

    //ward has 10 beds
    //database has 10 beds already initialised

    JPanel availableBeds=new JPanel();

    JLabel bed_id_Labels;

    private connectDatabase conn=new connectDatabase();

    public assignBed() throws SQLException, URISyntaxException {

    }

    public void setBedUnavailable(String bed_id_in) throws SQLException {
        conn.getConnection();
        Statement s=conn.createStatement();

        String sql="UPDATE beds SET availability=false where bed_id='"+bed_id_in+"' ";
        s.execute(sql);

    }

    public void setBedAvailable(String bed_id_in) throws SQLException{
        conn.getConnection();
        Statement s=conn.createStatement();

        String sql="UPDATE beds SET availability=true where bed_id='"+bed_id_in+"'";
        s.execute(sql);
    }

    public JPanel getAvailableBeds() throws SQLException {

        conn.getConnection();
        Statement s=conn.createStatement();

        String sqlStr="SELECT bed_id from beds where availability=true";
        ResultSet rset= s.executeQuery(sqlStr);

        while (rset.next()){
            bed_id_Labels=new JLabel(rset.getString("bed_id")+" ");
            availableBeds.add(bed_id_Labels);
            System.out.println(rset.getString("bed_id"));
        }

        return availableBeds;
    }

    public boolean isBedEmpty(String bed_in) throws SQLException {

        boolean emptyBed=true;
        conn.getConnection();
        Statement s=conn.createStatement();
        String sql="SELECT availability from beds where bed_id='"+bed_in+"' ";
        ResultSet rset=s.executeQuery(sql);

        while(rset.next()){
            if(rset.getBoolean("availability")==false){
                emptyBed=false;
            }
        }
        return emptyBed;
    }

}