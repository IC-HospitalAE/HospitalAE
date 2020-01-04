package database_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class initialiseDB {

    private Connection conn;
    String url="jdbc:postgresql://localhost/";
    String dbUrl="jdbc:postgresql://localhost/hospitalae";
    String password="pass";
    String username="user";

    public initialiseDB() throws SQLException {
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn= DriverManager.getConnection(url);

        //create database
        try {
            Statement s=conn.createStatement();
            String createDB = "create DATABASE hospitalae";
            s.executeUpdate(createDB);
        }
        catch (Exception e){
        }

        conn=DriverManager.getConnection(dbUrl);

        //create bed table
        try {
            String createBedsTable=" CREATE TABLE hospitalae.public.beds(availability boolean,id integer NOT NULL, bed_id integer NOT NULL,patient_id character varying(128),doctor_id character varying(128),check_in_time character varying(128));";

            //initialise sequence
            String bedSeq="CREATE SEQUENCE public.beds_id_seq AS integer START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;";
            String bedSeq2="ALTER SEQUENCE public.beds_id_seq OWNED BY public.beds.id;";
            String bedSeq3="ALTER TABLE ONLY public.beds ALTER COLUMN id SET DEFAULT nextval('public.beds_id_seq'::regclass);";
            String bedSeq4="ALTER TABLE ONLY public.beds ADD CONSTRAINT id PRIMARY KEY (id);";

            //add 10 bed rows into table
            String initilaisebed1="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,1,'','','') ";
            String initilaisebed2="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,2,'','','') ";
            String initilaisebed3="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,3,'','','') ";
            String initilaisebed4="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,4,'','','') ";
            String initilaisebed5="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,5,'','','') ";
            String initilaisebed6="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,6,'','','') ";
            String initilaisebed7="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,7,'','','') ";
            String initilaisebed8="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,8,'','','') ";
            String initilaisebed9="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,9,'','','') ";
            String initilaisebed10="INSERT INTO public.beds (availability, bed_id, patient_id, doctor_id, check_in_time) values (true,10,'','','') ";

            Statement s=conn.createStatement();
            s.executeUpdate(createBedsTable);
            s.executeUpdate(bedSeq);
            s.executeUpdate(bedSeq2);
            s.executeUpdate(bedSeq3);
            s.executeUpdate(bedSeq4);

            s.executeUpdate(initilaisebed1);
            s.executeUpdate(initilaisebed2);
            s.executeUpdate(initilaisebed3);
            s.executeUpdate(initilaisebed4);
            s.executeUpdate(initilaisebed5);
            s.executeUpdate(initilaisebed6);
            s.executeUpdate(initilaisebed7);
            s.executeUpdate(initilaisebed8);
            s.executeUpdate(initilaisebed9);
            s.executeUpdate(initilaisebed10);

            s.close();
        }
        catch (Exception e){
        }

        //create doctor table
        try {
            String createDrTable=" CREATE TABLE hospitalae.public.doctors (id integer NOT NULL ,firstname character varying(128) NOT NULL,lastname character varying(128) NOT NULL, identitynumber character varying(32) NOT NULL,  email character varying(128) NOT NULL, workload character varying(10) NOT NULL, availability boolean, num_patients integer,shift varchar (50));";

            //initialise sequence
            String DrSeq="CREATE SEQUENCE public.doctors_id_seq AS integer START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;";
            String drSeq2="ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;";
            String drSeq3="ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);";
            String drSeq4="ALTER TABLE ONLY public.doctors ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);";

            String dr1="INSERT INTO public.doctors (firstname, lastname, identitynumber, email, workload, availability, num_patients, shift) values ('Yunpeng','Li','1','yupengli@hospital.co.uk','',false,0,'')";
            String dr2="INSERT INTO public.doctors (firstname, lastname, identitynumber, email, workload, availability, num_patients, shift) values ('Vimalan','Vijayasekaran','2','vimalanvijayasekaran@hospital.co.uk','',false,0,'')";
            String dr3="INSERT INTO public.doctors (firstname, lastname, identitynumber, email, workload, availability, num_patients, shift) values ('Yilin','Huang','3','yilinhuang@hospital.co.uk','',false,0,'')";
            String dr4="INSERT INTO public.doctors (firstname, lastname, identitynumber, email, workload, availability, num_patients, shift) values ('Hongzhang','Chen','4','hongzhangchen@hospital.co.uk','',false,0,'')";


            Statement s=conn.createStatement();

            s.executeUpdate(createDrTable);
            s.executeUpdate(DrSeq);
            s.executeUpdate(drSeq2);
            s.executeUpdate(drSeq3);
            s.executeUpdate(drSeq4);

            s.executeUpdate(dr1);
            s.executeUpdate(dr2);
            s.executeUpdate(dr3);
            s.executeUpdate(dr4);


        }
        catch (Exception e){

        }

        try {
            String createPatientTable="CREATE TABLE hospitalae.public.patients ( id integer NOT NULL, phonenumber character varying(32) NOT NULL,  identitynumber character varying(50) NOT NULL, age character varying(3) NOT NULL, notes text, admit_status boolean, bednumber character varying(5),time_date character varying(255),discharge_time character varying(255),firstname character varying(128) NOT NULL,lastname character varying(128) NOT NULL,doctor_incharge character varying(128));\n";

            //initialise sequence
            String PatientSeq="CREATE SEQUENCE public.patients_id_seq AS integer START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;";
            String pSeq2="ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients.id;";
            String pSeq3="ALTER TABLE ONLY public.patients ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);";
            String pSeq4="ALTER TABLE ONLY public.patients ADD CONSTRAINT id PRIMARY KEY (id);";

            Statement s=conn.createStatement();
            s.executeUpdate(createPatientTable);
            s.executeUpdate(PatientSeq);
            s.executeUpdate(pSeq2);
            s.executeUpdate(pSeq3);
            s.executeUpdate(pSeq4);

            conn.close();
        }
        catch (Exception e){
        }

    }
}