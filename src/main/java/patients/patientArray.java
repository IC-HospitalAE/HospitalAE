package patients;

import JSON.Patient;

import java.util.ArrayList;

public class patientArray {
    private ArrayList<Patient> patientArrayList =new ArrayList<>();//Array stores all doctors

    public void addPatient(Patient p){
        patientArrayList.add(p);
    }

    public void printPatient(Patient d){

        for(Patient patient: patientArrayList){
            System.out.println("First: " +patient.getPatientname());
            System.out.println("Last: "+patient.getPatienLasttName());
            System.out.println("ID: " +patient.getPateintID());
            System.out.println("Age: " +patient.getPatientAge());
            System.out.println("Notes: "+patient.getDisease());
            System.out.println("Check in time:"+patient.getCheckin_time());
            System.out.println("Bed ID: "+patient.getBedID());
        }
    }
}
