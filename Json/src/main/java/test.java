import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ItemManagementService itemManagerService = new Registration_formPage();
        ArrayList<Patient> new_PatientList = itemManagerService.createListOfItems();
        Register_patient.getInstance().addPatient(new_PatientList);
        System.out.println(Register_patient.getInstance().printListofPatients());
    }
}
