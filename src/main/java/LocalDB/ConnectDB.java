package LocalDB;

public class ConnectDB {
    create table patients (
            id SERIAL PRIMARY KEY,
            familyname varchar(128) NOT NULL,
    givenname varchar(128) NOT NULL,
    phonenumber varchar(32)
);


    insert into patients (familyname,givenname,phonenumber) values('Jones','Bill','07755678899');
    insert into patients (familyname,givenname,phonenumber) values('Smith','John','07755671111');
    insert into patients (familyname,givenname,phonenumber) values('Mark','Wright','07755678899');



    select phonenumber from patients where givenname='Bill';


    select * from patients where givenname='Bill';

    select phonenumber, id, givenname from patients where givenname!='Bill';
    select * from patients where id >= 2;
    select * from patients where givenname in ('John','Bill');

    select * from patients where id=1;
    update patients set phonenumber='+4476886379' where id=1;
    select * from patients where id=1;
    update patients set phonenumber='+4472222479' where givenname='Bill';
    select * from patients where givenname='Bill';

    create table doctors (
            id SERIAL PRIMARY KEY,
            familyname varchar(128) NOT NULL,
    givenname varchar(128) NOT NULL,
    pagernum varchar(32)
);
    insert into doctors (familyname,givenname,pagernum) values('Hams','Dr','07755670000');
    insert into doctors (familyname,givenname,pagernum) values('Hamzz','Dr','07755670001');


    create table patientofdoctor ( id serial primary key,
                                   patientid int NOT NULL,
                                   doctorid int NOT NULL );

    insert into patientofdoctor (patientid,doctorid) values (1,2);
    insert into patientofdoctor (patientid,doctorid) values (2,2);
    insert into patientofdoctor (patientid,doctorid) values (3,1);

    select * from patientofdoctor inner join patients on(patients.id=patientofdoctor.patientid) where doctorid=2;

    select patients.familyname, doctors.familyname from patientofdoctor inner join patients on(patients.id=patientofdoctor.patientid) inner join doctors on(doctors.id=patientofdoctor.doctorid)
    where doctorid=2;
}
