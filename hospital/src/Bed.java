

public class Bed {
    private String PatientID;
    private String DoctorID;
    private int BedID;
    private boolean Attribute;

    public Bed(String PatientID, String DoctorID, int BedID, boolean Attribute){
        this.PatientID = PatientID;
        this.DoctorID = DoctorID;
        this.BedID = BedID;
        this.Attribute = Attribute;

    }

    public String getPatientID(){
        return PatientID;

    }

    public String getDoctorID(){
        return DoctorID;
    }

    public int getBedID() {
        return BedID;
    }

    public boolean getAttribute() {
        return Attribute;
    }

    public void checkinPatient(String PatientID,boolean Attribute) {
        this.PatientID = PatientID;
        this.Attribute = Attribute;


    }

    public void checkoutPatient(String PatientID, String DoctorID, int BedID, boolean Attribute) {
        if (Attribute==true) {
            this.PatientID = "NA";
            this.DoctorID = "NA";
            this.BedID = BedID;
            this.Attribute = false;

        }

    }
    public void AssignDoctor(String PatientID, String DoctorID, boolean Attribute) {
        if (Attribute==true) {
            this.DoctorID = DoctorID;
        }
    }


}
