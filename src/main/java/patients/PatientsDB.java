package patients;
import java.util.ArrayList;

public class PatientsDB {
    private ArrayList<Patients> patientsList=new ArrayList<>();

    public int getNumPatient(){
        return patientsList.size();
    }

    public void addPatient( Patients p){
        patientsList.add(p);
    }

    public void printDB(){
        for(Patients p:patientsList){
            System.out.println("Name: "+p.getName());
            System.out.println("Age: "+p.getAge());
            System.out.println("ID: "+p.getID());
            System.out.println("Notes: "+p.getNotes());
            System.out.println("Phone number: "+p.getPhonenumber());
        }
    }
}
