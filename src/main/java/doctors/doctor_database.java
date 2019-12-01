package doctors;

import java.util.ArrayList;

public class doctor_database {

    private ArrayList<Doctor> doctorList =new ArrayList<>();//Array stores all doctors
    private int numDoctors = doctorList.size();


    public int getNumDoctor(){
        return numDoctors;
    }

    public void addDoctor(Doctor d){
        doctorList.add(d);
    }

    public void PrintDoctor(Doctor d){
        for(Doctor doc: doctorList){
            System.out.println("First: " +doc.getFirstname());
            System.out.println("Last: "+doc.getLastname());
            System.out.println("ID: " +doc.getId());
            System.out.println("Workload: " +doc.getWorkload());
        }
    }

    public void arrangeTimetable(int patientsNum){
        TimeTable tB = new TimeTable(patientsNum, numDoctors);
        String[][] workSlot= new String[numDoctors][10];


        for(int k = 0; k < doctorList.size();k++) {
            doctorList.get(k).setTimeSlot(workSlot[k]);
        }
    }
}
