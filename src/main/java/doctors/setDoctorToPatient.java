package doctors;

import database_conn.connectDatabase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class setDoctorToPatient {
    gettingAvailableDrs getDr= new gettingAvailableDrs();
   private ArrayList<String>doctosAvailable=getDr.getAvailableDr();
   private ArrayList<String> drNot=getDr.getNotAvail();
   private ArrayList<String> patientsList=new ArrayList<>();

   //store the doctor and patient as pairs
   private HashMap<String,String>doctorWithPatient=new HashMap<>();

   private connectDatabase conn;

    public setDoctorToPatient() throws IOException, SQLException {
        conn = new connectDatabase();
        conn.getConnection();
        Statement s = conn.createStatement();

        //set the drs that are not available to not true
        for (String name : doctosAvailable) {
            String[] split = name.split(" ");
            System.out.println(split[0]);
            String sql = "UPDATE doctors SET availability=true WHERE firstname='" + split[0] + "'";
            s.execute(sql);
        }

        //set the drs that are not available to not false
        for (String name : drNot) {
            String[] split = name.split(" ");
            String sql = "UPDATE doctors SET availability=false WHERE firstname='" + split[0] + "'";
            s.execute(sql);
        }
        conn.close();

        setDrToP();
    }


    public void setDrToP() throws SQLException {

        conn=new connectDatabase();
        conn.getConnection();
        Statement s=conn.createStatement();

        //get patient without dr incharge of them
        String sql="SELECT firstname from patients WHERE admit_status=true AND doctor_incharge='';";
        ResultSet rset=s.executeQuery(sql);
        while (rset.next()){
            String name=rset.getString("firstname");
            patientsList.add(name);
        }

        int numOfPatients=patientsList.size(); //num of patients
        int numOfDr=doctosAvailable.size(); //num of drs
        int minNumOfDr=numOfPatients/4; //since each dr can look up to 4 patients

        //if there are more than the minimum num of drs
        if(minNumOfDr<numOfDr){

            conn.getConnection();
            Statement s1=conn.createStatement();

            for(String name:doctosAvailable) {
                String sql1="SELECT num_patients FROM doctors where availability=true";
                ResultSet rset1=s1.executeQuery(sql1);

                while (rset.next()){
                    int n=rset.getInt("num_patients");


                }
            }
        }else{
            System.out.println("send email");
        }
    }


}
