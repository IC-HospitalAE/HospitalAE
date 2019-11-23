package patients;
public class Patients {

    private String name; //store name
    private String age; //store age
    private String notes; //store reception notes
    private String ID; //ID
    private String phonenumber;
    boolean discharged; //state of admission

    private PatientsDB DB=new PatientsDB();

    public Patients(String name_in,String age_in,String notes_in,String ID_in,String num_in){
        this.name=name_in;
        this.age=age_in;
        this.notes=notes_in;
        this.ID=ID_in;
        this.phonenumber=num_in;
        discharged=false;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phn_num) {
        this.phonenumber = phn_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_in) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age_in) {
        this.age = age;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes_in) {
        this.notes = notes;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID_in) {
        this.ID = ID_in;
    }

    public boolean isDischarged() { //return weather patient is admited or not
        return discharged;
    }

    public void setDischarged(boolean discharged) { //set admission status
        this.discharged = discharged;
    }
}
