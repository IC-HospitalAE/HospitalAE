package doctors;

import database_conn.connectDatabase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class updateLocalDoctorDB {
    gettingAvailableDrs getDr= new gettingAvailableDrs();

   private ArrayList<String> doctorsAvailable =getDr.getAvailableDr();
   private ArrayList<String> drNot=getDr.getNotAvailableToday();
   private ArrayList<String> drsOnNextShift=getDr.getAvailableNextShift();

   private ArrayList<String> patientsList=new ArrayList<>();

   private connectDatabase conn;

   private sendEmail sendemail;

   public updateLocalDoctorDB() throws SQLException, IOException {
       setDrAvailability();
       sendEmailToDr();
   }

    public void setDrAvailability() throws SQLException {
        conn = new connectDatabase();
        conn.getConnection();
        Statement s = conn.createStatement();

        //set the drs that are not available to not true
        for (String name : doctorsAvailable) {
            String[] split = name.split(" ");
            String sql = "UPDATE doctors SET availability=true WHERE firstname='" + split[0] + "'";
            s.execute(sql);
        }

        //set the drs that are not available to not false
        for (String name : drNot) {
            String[] split = name.split(" ");
            String sql = "UPDATE doctors SET availability=false WHERE firstname='" + split[0] + "'";
            s.execute(sql);
        }

        //set the drs available on next shift
        for(String name: drsOnNextShift){
            String[] split = name.split(" ");
            String sql="UPDATE doctors SET shift='Not Today' WHERE id>0 "; //clear the field
            String sql1 = "UPDATE doctors SET shift='Right after this shift' WHERE firstname='" + split[0] + "'"; //add the shift
            s.execute(sql);
            s.execute(sql1);
        }
        conn.close();
    }

    public void sendEmailToDr() throws SQLException {

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
        int numOfDr= doctorsAvailable.size(); //num of drs
        int minNumOfDr=numOfPatients/4; //since each dr can look up to 4 patients

        //if there are less dr available than the number of doctors needed
        //send email
        if(numOfDr<minNumOfDr){
            sendemail=new sendEmail();
        }else{

        }
    }
}
