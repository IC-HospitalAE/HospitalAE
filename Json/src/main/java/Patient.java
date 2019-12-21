import java.io.Serializable;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


import java.util.Scanner;
public class Patient extends Person implements Serializable {

    public int patientAge;
    public String patientGender;
    public String disease;
    public String checkin_time;

    public Patient(String name,String last_name,String id,String phone,int patientAge,String patientGender,String disease,String checkin_time){
        super(name,last_name, id,phone);
        this.patientAge=patientAge;
        this.patientGender=patientGender;
        this.disease=disease;
        this.checkin_time = checkin_time;
    }


    public int getPatientAge(){return patientAge;}
    public String getPatientGender(){return patientGender;}
    public String getDisease(){return disease;}
    public String getCheckin_time(){return checkin_time;}

}

