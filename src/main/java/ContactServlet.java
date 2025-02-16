import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from form
        String phoneNumber = request.getParameter("phoneNumber");
        String name = request.getParameter("name");

//        String encodedPhoneNumber = URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8);
//        String encodedName = name != null ? URLEncoder.encode(name, StandardCharsets.UTF_8) : "";


        // Determine API URL based on form input
        String apiUrl;
        if (name != null && !name.isEmpty()) {
            apiUrl = "http://localhost:8080/contact_registry_war_exploded/api/contact/" + phoneNumber + "/" + name;
        } else {
            apiUrl = "http://localhost:8080/contact_registry_war_exploded/api/contact/" + phoneNumber;
        }
        System.out.println("apiurl "+apiUrl);
        System.out.println("encodedPhoneNumber "+phoneNumber);
        System.out.println("encodedName "+name);
        // Make API request
        String jsonResponse = fetchContact(apiUrl);
        System.out.println("jsonResponse "+jsonResponse);

        // Set response attribute and forward to result page
        request.setAttribute("contactData", jsonResponse);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    // Method to fetch data from API
    private String fetchContact(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Read response
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String response = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        return response;
    }
}
