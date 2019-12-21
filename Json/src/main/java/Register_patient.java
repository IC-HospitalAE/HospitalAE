import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Register_patient {
    private static final Register_patient INSTANCE = new Register_patient();
    private List<Patient> PatientList = new ArrayList<>();

    private Register_patient() {
        // Private Constructor
        // will prevent the instantiation of this class directly
    }

    public static Register_patient getInstance() {
        return INSTANCE;
    }

    public void addPatient(ArrayList<Patient> new_PatientList) {
        PatientList.addAll(new_PatientList);
    }

    public String printListofPatients() {

        StringBuilder sb = new StringBuilder();

        for (Patient patient : PatientList) {
            sb.append(" [NAME : " +patient.getName()+", Last_name : "+ patient.getLast_name()+",phone : "+patient.getPhone()+"]");
        }
        return sb.toString();
    }



}

