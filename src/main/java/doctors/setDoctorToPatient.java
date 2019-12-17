package doctors;

import database_conn.connectDatabase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class setDoctorToPatient {
    gettingAvailableDrs getDr= new gettingAvailableDrs();

   private ArrayList<String>doctosAvailable=getDr.getAvailableDr();
   private ArrayList<String> drNot=getDr.getNotAvail();
   private ArrayList<String> patientsList=new ArrayList<>();

   private connectDatabase conn;

    public setDoctorToPatient() throws IOException, SQLException {
        conn=new connectDatabase();
        conn.getConnection();
        Statement s=conn.createStatement();
        //set the drs that are not available to not true
        for(String name:doctosAvailable){
            String[] split=name.split(" ");
            System.out.println(split[0]);
            String sql="UPDATE doctors SET availability=true WHERE firstname='"+split[0]+"'";
            s.execute(sql);
        }

        //set the drs that are not available to not false
        for(String name:drNot){
            String[] split=name.split(" ");
            String sql="UPDATE doctors SET availability=false WHERE firstname='"+split[0]+"'";
            s.execute(sql);
        }

        conn.close();

        setDrToP();

        //if bed isnt empty && no dr assigned
        //assign on duty dr to that be
    }


    public void setDrToP() throws SQLException {
        //if patient no dr assigned
        //assign a dr
        //increase the count in dr
        //if count>4 set full

        conn=new connectDatabase();
        conn.getConnection();
        Statement s=conn.createStatement();

        //get patient without dr incharge of them
        String sql="SELECT firstname from patients WHERE admit_status=true AND doctor_incharge='' OR doctor_incharge=null ;";
        ResultSet rset=s.executeQuery(sql);

        while (rset.next()){
            String name=rset.getString("firstname");
            patientsList.add(name);
        }

        //assign dr to the patient
        for(String name:patientsList){

        }



        conn.close();

    }
}
