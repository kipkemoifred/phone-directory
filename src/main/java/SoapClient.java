

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SoapClient {
    private static final String SOAP_ENDPOINT = "http://localhost:8080/contact_registry_war_exploded/ContactSOAPService"; // Change this to actual SOAP URL

    public static String sendSoapRequest(String organizationName) throws Exception {
        // Create SOAP XML request dynamically
        String soapRequest = "<?xml version=\"1.0\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soap:Body>\n" +
                "        <getContactsRequest>\n" +
                "            <organizationName>" + organizationName + "</organizationName>\n" +
                "        </getContactsRequest>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>";

        // Connect to SOAP web service
        URL url = new URL(SOAP_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setDoOutput(true);

        // Send request
        OutputStream os = connection.getOutputStream();
        os.write(soapRequest.getBytes());
        os.close();

        // Read response
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder soapResponse = new StringBuilder();

        while ((line = br.readLine()) != null) {
            soapResponse.append(line);
        }
        br.close();

        return soapResponse.toString();
    }
}

