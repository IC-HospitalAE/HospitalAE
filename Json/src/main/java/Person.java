public class Person {
    public String name;
    public String last_name;
    public String phone;
    public String id;

    public Person(String name,String last_name,String id,String phone){
        this.name=name;
        this.phone=phone;
        this.last_name=last_name;
        this.id=id;
    }

    public String getName(){return name;}
    public String getId(){return id;}

    public String getLast_name(){return last_name;}

    public String getPhone(){return phone;}
}
