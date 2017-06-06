import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;

public class Main {

    private static final String TEST_URL = "https://api.backendless.com/19F545A4-DAA2-044A-FFDC-0300DFB02600/42124A9D-82A3-E574-FF4A-32D554A4B400/services/DemoService/0.0.0__debug/Greeting";
    private static final String PARAM = "?guestName=%22{0}%22";
    //private static final String TEST_URL = "https://api.backendless.com/19F545A4-DAA2-044A-FFDC-0300DFB02600/42124A9D-82A3-E574-FF4A-32D554A4B400/services/DemoService/0.0.0__debug/Greeting?guestName=qqq";

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World! Backendless call");
        long startDate =  new Date().getTime();
        testGet("ss");
        long endDate =  new Date().getTime();
        System.out.println("testGet(): " + (endDate - startDate) + " ms");
    }

    private static void testGet(String name) throws Exception {
        String url = TEST_URL + MessageFormat.format(PARAM, name);
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "appliaction/json");
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }
}
