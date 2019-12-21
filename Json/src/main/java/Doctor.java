public class Doctor extends Person {

    private boolean state; //says id dr is
    private String workload;
    // List<Patient> doctorPatientList= new ArrayList<Patient>();
    public Doctor(String name, String last_name, String id, String phone, boolean state, String workload){
        super(name,last_name, id,phone);
        this.state=state;
        this.workload = workload;
    }

    public boolean isState() { return state; }
    public void setState(boolean state) { this.state = state; }
    public String getWorkload() { return workload; }
    public void setWorkload(String workload) {this.workload = workload;}
}
