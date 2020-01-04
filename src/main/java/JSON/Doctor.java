package JSON;

public class Doctor extends Person {

    private boolean state; //says id dr is
    private String workload;
    //private time_availability;


    public Doctor(String firstn,String lname, String ID){
        super(firstn,lname,ID);
    }

    public String getLastname() {
        return last_name;
    }

    public String getFirstname() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getWorkload() {
        return workload;
    }
}
