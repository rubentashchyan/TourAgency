package Parser;

import Entity.Booking;
import Entity.Client;
import lombok.AllArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class ClientXMLParser{
    private String pathToFile;


    public List <Client> parseAll() throws ParserConfigurationException, IOException, SAXException, ParseException {
        List<Client> clients = new ArrayList<>();
        File file = new File(pathToFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("client");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node clientNode = nodeList.item(i);
            System.out.println(nodeList.item(i));
            if (clientNode.getNodeType() == Node.ELEMENT_NODE) {
                Element clientElement = (Element) clientNode;
                String clientID = clientElement.getAttribute("id");
                String name = clientElement.getElementsByTagName("name").item(0).getTextContent();
                String email = clientElement.getElementsByTagName("email").item(0).getTextContent();
                String phone = clientElement.getElementsByTagName("phone").item(0).getTextContent();
                NodeList bookingNode = ((Element) clientNode).getElementsByTagName("booking");
                ArrayList<Booking> bookings = new ArrayList<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (int j = 0; j < bookingNode.getLength(); j++) {
                    Element bookingElement = (Element) bookingNode.item(j);
                    String tourID = bookingElement.getAttribute("tourID");
                    Date date = dateFormat.parse(bookingElement.getAttribute("date"));
                    Booking booking = new Booking(tourID, date);
                    bookings.add(booking);
                }
                Client client = new Client(clientID, name, email, phone, bookings);
                clients.add(client);
            }
        }

        return clients;
    }


    public void save(Client entity) throws IOException {
        File file = new File("C:\\Users\\modar\\OneDrive\\Рабочий стол\\travelAgency.xml");
        file.setWritable(true);
        FileWriter writer = new FileWriter(file, true);

        writer.write("<client id=\"" + entity.getClientID() + "\">\r\n");
        writer.write("<name>" + entity.getName() + "</name>\r\n");
        writer.write("<email>" + entity.getEmail() + "</email>\r\n");
        writer.write("<phone>" + entity.getPhone() + "</phone>\r\n");
        writer.write("<bookings>\r\n");
        for (Booking booking : entity.getBookings()) {
            writer.write("<booking tourId=\"" + booking.getTourID() + "\" date=\"" + booking.getDate() + "\" />\r\n");
        }
        writer.write("</bookings>\r\n");

        writer.write("</client>\r\n");


    }


    public void saveAll(List <Client> entities) throws IOException {

        for (int i = 0; i < entities.size(); i++) {
            Client entity = entities.get(i);
            save(entity);

        }
    }

}

