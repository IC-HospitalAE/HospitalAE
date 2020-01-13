package doctors;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class gettingAvailableDrs {
    private clientDoctor dr =new clientDoctor();

    //gets the dr and their timetable
    private HashMap<String,String>doctors=dr.getDoctorAndTimetable();

    private String dayToday;
    private String timeNow;

    //DEBUG
    private String testTime="09";
    private String TESTDAY="Thurs";

    private ArrayList<String> allDoctors =new ArrayList<>(); //all doctors on the db
    private ArrayList<String>availableDr=new ArrayList<>(); //drs available now
    private ArrayList<String>availableNextShift=new ArrayList<>(); //drs available on next shift;
    private ArrayList<String>notAvailableToday=new ArrayList<>(); //drs not available today
    private ArrayList<Integer>drWorkloads=new ArrayList<>();//get the drs workload

    public gettingAvailableDrs() throws IOException, SQLException, URISyntaxException {
        decodeTime();

    }

    private void decodeTime() throws SQLException {
        setCurrentTime();


        for(Map.Entry<String, String> entry : doctors.entrySet()) {
            String tt = entry.getValue();
            String drname = entry.getKey();

            allDoctors.add(drname); //stores dr names into an array

            StringBuilder bld = new StringBuilder();

            String[] splitTimetable = tt.split(" ");

            //converts the coded timetable to Java standard date and time
            //this will allows to check if dr is on duty
            for (int i = 0; i < splitTimetable.length; i++) {

                switch (splitTimetable[i]) {
                    case "1a":
                        String time = "Mon 00";
                        splitTimetable[i] = time;
                        break;
                    case "1b":
                        String time2 = "Mon 08";
                        splitTimetable[i] = time2;
                        break;
                    case "1c":
                        String time3 = "Mon 16";
                        splitTimetable[i] = time3;
                        break;
                    case "2a":
                        String time4 = "Tue 00";
                        splitTimetable[i] = time4;
                        break;
                    case "2b":
                        String time5 = "Tue 08";
                        splitTimetable[i] = time5;

                        break;
                    case "2c":
                        String time6 = "Tue 16";
                        splitTimetable[i] = time6;

                        break;
                    case "3a":
                        String time7 = "Wed 00";
                        splitTimetable[i] = time7;

                        break;
                    case "3b":
                        String time21 = "Wed 08";
                        splitTimetable[i] = time21;

                        break;
                    case "3c":
                        String time8 = "Wed 16";
                        splitTimetable[i] = time8;
                        break;
                    case "4a":
                        String time9 = "Thu 00";
                        splitTimetable[i] = time9;
                        break;
                    case "4b":
                        String time10 = "Thu 08";
                        splitTimetable[i] = time10;
                        break;
                    case "4c":
                        String time11 = "Thu 16";
                        splitTimetable[i] = time11;
                        break;
                    case "5a":
                        String time12 = "Fri 00";
                        splitTimetable[i] = time12;
                        break;
                    case "5b":
                        String time13 = "Fri 08";
                        splitTimetable[i] = time13;
                        break;
                    case "5c":
                        String time14 = "Fri 16";
                        splitTimetable[i] = time14;
                        break;
                    case "6a":
                        String time15 = "Sat 00";
                        splitTimetable[i] = time15;
                        break;
                    case "6b":
                        String time16 = "Sat 08";
                        splitTimetable[i] = time16;
                        break;
                    case "6c":
                        String time17 = "Sat 16";
                        splitTimetable[i] = time17;
                        break;
                    case "7a":
                        String time18 = "Sun 00";
                        splitTimetable[i] = time18;
                        break;
                    case "7b":
                        String time19 = "Sun 08";
                        splitTimetable[i] = time19;
                        break;
                    case "7c":
                        String time20 = "Sun 16";
                        splitTimetable[i] = time20;
                        break;
                    default:
                        System.out.println("Invalid");
                }
                bld.append(splitTimetable[i]+",");
            }

            String timetable=bld.toString();

            //replaces coded timetable to new one in hashmap
            doctors.replace(drname,tt,timetable);
        }


        //loops through hashmap and send the name to the function to determine if dr is available
        for(Map.Entry<String, String> entry : doctors.entrySet()){
            //send each dr time timetable
            //get if the dr is available
            //send dr name
            whichDrisAvailableNow(entry.getKey());
        }
    }

    //fucntion to set the current time to that of the system's
    private void setCurrentTime(){
        //get the current time from the system
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH"); //get only the hours
        timeNow=timeFormat.format(date);

        dayToday=new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
        StringBuilder bld= new StringBuilder();
        bld.append(dayToday+" "+timeNow);
        String timeDate = bld.toString();
    }

    private void whichDrisAvailableNow(String name){

        //get the doctor's timetable
        String timeTable= doctors.get(name);

        //if the doctor's timetable has todays day we then check the shift-time
        if(timeTable.contains(dayToday)){

            String[] split=timeTable.split(",");

            for(int i=0;i<split.length;i++){

                String[] time=split[i].split(" ");
                String day=time[0];
                String times=time[1];
                int timeDr=Integer.parseInt(times);
                int timenow=Integer.parseInt(timeNow);

                //checks which shift NOW
                timenow=checkTimeShift(timenow);

                //check which shift dr
                timeDr=checkTimeShift(timeDr);

                if( (day.equals(dayToday)) && timenow==timeDr){
                    availableDr.add(name); //dr avaiable today and on shift now

                }else if( day.equals(dayToday) && timeDr==timenow+1 ){
                    availableNextShift.add(name); //next shift doctor
                }else{

                }
            }
        }else{
            //doctors not available today
            notAvailableToday.add(name);

        }

        for(String n:allDoctors){
            setWorkload(n);

        }
    }

    //check which time shift the entered time is on
    private int checkTimeShift(int time_in){
        //return 1 if shift 1
        //return 2 if shift 2
        //return 3 if shift 3

        if(time_in>=0&&time_in<8){
            return 1;
        }else if(time_in>=8&&time_in<16){
            return 2;
        }else
            return 3;
    }

    //calculated the workload of the doctor
    private void setWorkload(String name_in){
        String workload=doctors.get(name_in);
        workload=workload.replaceAll(",","");
        workload=workload.replaceAll("\\s","");

        int workLoad=workload.length()/5;
        drWorkloads.add(workLoad);
    }

    //this are getters for other classes to access

    public ArrayList<String>getAllDoctors(){return allDoctors;}
    public ArrayList<String> getAvailableDr(){
        return availableDr;
    }
    public ArrayList<Integer> getDrWorkloads(){return drWorkloads;}
    public ArrayList<String> getNotAvailableToday(){
        return notAvailableToday;
    }
    public ArrayList<String> getAvailableNextShift(){
        return availableNextShift;
    }

}
