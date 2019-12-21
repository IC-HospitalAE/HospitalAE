import java.util.ArrayList;
import java.util.Scanner;

public class Registration_formPage implements ItemManagementService{
    @Override
    public ArrayList<Patient> createListOfItems() {

        ArrayList<Patient> new_PatientList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            do{
                System.out.println("ENTER NAME");
                String pname = scanner.nextLine();
                System.out.println("ENTER Last name");
                String plast_name = scanner.nextLine();
                System.out.println("ENTERid");
                String p_id = scanner.nextLine();
                System.out.println("ENTER phone");
                String p_phone = scanner.nextLine();
                System.out.println("ENTER age");
                int p_age = Integer.parseInt(scanner.nextLine());
                System.out.println("ENTER gender");
                String p_gender = scanner.nextLine();
                System.out.println("ENTER disease");
                String p_disease = scanner.nextLine();
                System.out.println("ENTER check in");
                String p_checkin = scanner.nextLine();


                Patient patient = new Patient(pname, plast_name, p_id, p_phone, p_age, p_gender, p_disease, p_checkin);
                new_PatientList.add(patient);

                System.out.println("Continue?[Y/N]");
                scanner.nextLine();
            }while (scanner.nextLine().equalsIgnoreCase("y"));

        } finally {

            scanner.close();
        }
        return new_PatientList;
    }


}

