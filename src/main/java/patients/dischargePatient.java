package patients;

import database_conn.connectDatabase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Statement;

public class dischargePatient {

    public void discharge(String name,String bednum,String dischageDate,String drname) throws SQLException, IOException, URISyntaxException {

        System.out.println(drname);
        String[] drFirstname=drname.split(" ");

        //connect to database
        connectDatabase conn1=new connectDatabase();

        conn1.getConnection();
        Statement statement=conn1.createStatement();

        String sqlStr = "UPDATE patients SET admit_status=false,bednumber='',discharge_time='"+dischageDate+"' WHERE firstname='" + name + "'; ";
        statement.execute(sqlStr);

        String sqlStr5="UPDATE beds SET availability=true WHERE bed_id='"+bednum+"' ";
        statement.execute(sqlStr5);

        String sqlStr2="UPDATE beds SET check_in_time='',patient_id='', doctor_id='' WHERE bed_id='"+bednum+"'   ";
        statement.execute(sqlStr2);

        String sqlStr7="UPDATE doctors SET num_patients=num_patients-1 WHERE firstname='"+drFirstname[1]+"'   ";
        statement.execute(sqlStr7);


        conn1.close();

    }

}

