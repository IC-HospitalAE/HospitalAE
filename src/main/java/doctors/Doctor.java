package doctors;

import java.awt.*;

public class Doctor {

    private String firstname;
    private String lastname;
    private String id;
    private boolean state; //says id dr is
    private String workload;
    //private time_availability;


    public Doctor(String firstn,String lname, String ID, String work){
       firstname=firstn;
       lastname=lname;
       id=ID;
       state=true; //initialse all dr as available
       workload=work;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }
}
