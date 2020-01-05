# HospitalAE
If you want your own jar file. Go to the scr package then go to database_conn 
Then go to initialiseDB class
For the line " conn= DriverManager.getConnection(url, "postgres", "password"); " change the "password" to your postgres db password.
Then go to build.gradle file, run the jar{} to generate the jar file for you.
Run the jar file and it should work.
