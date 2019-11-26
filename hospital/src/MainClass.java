import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        //instantiate bed
        //Bed bed = new Bed("patient1", "DrWho",1,true); //input variable here is only for testing
        ArrayList<Bed> bed = new ArrayList<Bed>(); //create an arraylist of type Bed

        //initialise empty beds 1-5

        Bed bed1 = new Bed("NA", "NA", 1, false);
        Bed bed2 = new Bed("NA", "NA",2,false);
        Bed bed3 = new Bed("NA", "NA",3,false);
        Bed bed4 = new Bed("NA", "NA",4,false);
        Bed bed5 = new Bed("NA", "NA",5,false);


        //add beds to arraylist
        bed.add(bed1);
        bed.add(bed2);
        bed.add(bed3);
        bed.add(bed4);
        bed.add(bed5);

        //checkin
        bed1.checkinPatient("001", true);
        System.out.println("-------------------------");
        System.out.println("checkin:");
        System.out.println(bed.get(0).getPatientID());
        System.out.println(bed.get(0).getDoctorID());
        System.out.println(bed.get(0).getBedID());
        System.out.println(bed.get(0).getAttribute());

        //assign doctor
        bed1.AssignDoctor("001", "Dr1",true);
        System.out.println("-------------------------");
        System.out.println("assign doctor:");
        System.out.println(bed.get(0).getPatientID());
        System.out.println(bed.get(0).getDoctorID());
        System.out.println(bed.get(0).getBedID());
        System.out.println(bed.get(0).getAttribute());

        //checkout patient
        bed1.checkoutPatient("001", "Dr1",1,true);
        System.out.println("-------------------------");
        System.out.println("checkout:");
        System.out.println(bed.get(0).getPatientID());
        System.out.println(bed.get(0).getDoctorID());
        System.out.println(bed.get(0).getBedID());
        System.out.println(bed.get(0).getAttribute());






        //bed 6-10 is empty
//        bed.add(new Bed("NA", "NA",6,false));
//        bed.add(new Bed("NA", "NA",7,false));
//        bed.add(new Bed("NA", "NA",8,false));
//        bed.add(new Bed("NA", "NA",9,false));
//        bed.add(new Bed("NA", "NA",10,false));





    }


}
