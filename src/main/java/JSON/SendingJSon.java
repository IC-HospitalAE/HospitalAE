package JSON;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
 The SendingJSon class is used to establish a connection with the servlet
  and submit the Patient data to the Servlet using Post*/


public class SendingJSon {
    public static void main(String[] args) throws IOException {
        GetRequest();//Calls GetRequest Method
        PostRequest();//Calls PostRequest Method
    }
    public static void GetRequest() { //GetRequest Method
        //To get URL of Heroku (request data from specified source) alan
        try {
            URL myURL = new URL("http://localhost:8080/Servlet123/patients");
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            //Reading Input
            BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
            String inputLine;
            // Read the body of the response
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void PostRequest(){//Post Method is used to send data  to the servlet(doctor page) to update the resource
        try {

            /*Here we should be sending the List of Patient Objects*/
            /* So basically, the code should be something like
            SubmitPatient submitpatient = new SubmitPatient
            String jsonString = gson.toJson(submitpatient);
            String message = jsonString;
             byte[] body = message.getBytes(StandardCharsets.UTF_8);
            */
            Patient p = new Patient("Wenli", "Zhou", "12345", "20",
                    "lactose intolerance", "23:23", "7718912039", "2");

            Gson gson = new Gson();
            String jsonString = gson.toJson(p);

            //Set up body data
            String message = jsonString;
            byte[] body = message.getBytes(StandardCharsets.UTF_8);
            URL myURL = new URL("http://localhost:8080/MyServlet/patients");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            // Set up the header
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
            // Write the body of the request
            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            // Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }
            bufferedReader.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }


}

