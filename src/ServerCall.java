import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by ivan on 6.6.17.
 */
public class ServerCall extends Thread {

    private static final String TEST_URL = "https://api.backendless.com/19F545A4-DAA2-044A-FFDC-0300DFB02600/42124A9D-82A3-E574-FF4A-32D554A4B400/services/DemoService/0.0.0__debug/Greeting";
    private static final String PARAM = "?guestName=%22{0}%22";

    private String name;
    public ServerCall(String name) {
        this.name = name;
    }

    @Override
    public void run()  {
        long startDate =  new Date().getTime();
        try {

            testGet();


        } catch (Exception e) {
            System.out.println("Thread: " + name + " " + e.getMessage());
        } finally {
            long endDate =  new Date().getTime();
            System.out.println("Thread: " + name + " testGet(): " + (endDate - startDate) + " ms");
        }

    }

    private void testGet() throws Exception {
        String url = TEST_URL + MessageFormat.format(PARAM, name);
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "appliaction/json");
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' name: " + name + " : " + "request to URL : " + url);
        System.out.println("Response Code : name: "+ name + " : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println("Thread: name: " + name + response.toString());
    }
}
