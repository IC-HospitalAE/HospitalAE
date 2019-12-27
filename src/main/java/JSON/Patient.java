package JSON;

import java.io.Serializable;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;
import UI.patientForm;

public class Patient extends Person implements Serializable {


    /*private ArrayList<Patient> PatientList = new ArrayList<Patient>();*/
    private String patientAge;
    private String disease;
    private String checkin_time;
    private String phone;
    private String bed;



    public Patient(String name,String last_name,String id,String patientAge,String disease,String checkin_time,String phone_in,String bed_in){
        super(name,last_name, id);
        this.patientAge=patientAge;
        this.disease=disease; /*notes=disease*/
        this.checkin_time = checkin_time;
        this.phone=phone_in;
        this.bed=bed_in;
    }

   /* public ArrayList<Patient> getPatientList(){
        return PatientList;
    }*/
    public String getPatientname(){
        return name;
    }
    public String getPatienLasttName(){
        return last_name;
    }
    public String getPateintID(){
        return id;
    }
    public String getPatientAge(){return patientAge;}
    public String getDisease(){return disease;}
    public String getCheckin_time(){return checkin_time;}
    public String getBedID(){ return bed; }



}

