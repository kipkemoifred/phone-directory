
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

@WebServlet("/SoapServlet")
public class SoapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get organization name from user input
        String organizationName = request.getParameter("organizationName");

        try {
            // Send SOAP request and get response
            String soapResponse = SoapClient.sendSoapRequest(organizationName);

            // Parse the SOAP response
            String parsedResponse = parseSoapResponse(soapResponse);

            // Forward response to JSP
            request.setAttribute("soapResponse", parsedResponse);
            request.getRequestDispatcher("response.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("soapResponse", "Error occurred while processing request.");
            request.getRequestDispatcher("response.jsp").forward(request, response);
        }
    }

    private String parseSoapResponse(String soapResponse) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new java.io.ByteArrayInputStream(soapResponse.getBytes()));

        NodeList contactNodes = doc.getElementsByTagName("contact");
        StringBuilder result = new StringBuilder("<h3>Contacts:</h3><ul>");

        for (int i = 0; i < contactNodes.getLength(); i++) {
            Element contactElement = (Element) contactNodes.item(i);

            String fullName = contactElement.getElementsByTagName("fullName").item(0).getTextContent();
            String phoneNumber = contactElement.getElementsByTagName("phoneNumber").item(0).getTextContent();
            String email = contactElement.getElementsByTagName("email").item(0).getTextContent();
            String organization = contactElement.getElementsByTagName("organization").item(0).getTextContent();

            result.append("<li><b>Name:</b> ").append(fullName)
                    .append(", <b>Phone:</b> ").append(phoneNumber)
                    .append(", <b>Email:</b> ").append(email)
                    .append(", <b>Organization:</b> ").append(organization)
                    .append("</li>");
        }
        result.append("</ul>");

        return result.toString();
    }
}
