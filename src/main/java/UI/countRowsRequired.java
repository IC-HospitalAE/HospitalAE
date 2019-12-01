package UI;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class countRowsRequired {
    private int count=0;
    private GridLayout gl;

    public void NumofRows(GridLayout g, ResultSet rset2) throws SQLException {

        while (rset2.next()){
            System.out.println(rset2.getInt("count"));
            count=rset2.getInt("count");
        }
        g.setRows(count); //set the row size
    }

    public void NumofRows_plus1(GridLayout g, ResultSet rset2) throws SQLException {

        while (rset2.next()){
            count=rset2.getInt("count");
        }
        g.setRows(count+1); //set the row size
    }
    public void NumofRows_plus(ResultSet rset) throws SQLException {
        while (rset.next()){
            count=rset.getInt("count");
        }
    }

    public int getCount() {
        return count;
    }
}
