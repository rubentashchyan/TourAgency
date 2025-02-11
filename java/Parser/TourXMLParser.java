package Parser;

import Entity.Client;
import Entity.Guide;
import Entity.Tour;
import lombok.Data;
import lombok.NoArgsConstructor;
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

@Data
@NoArgsConstructor
public class TourXMLParser {
    private String pathToFile;

    public TourXMLParser(String pathToFile) {
        this.pathToFile = pathToFile;
    }


    public List <Tour> parseAll() throws IOException, SAXException, ParserConfigurationException, ParseException {
        List<Tour> tours = new ArrayList<>();
        File file = new File(pathToFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("tour");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node toursNode = nodeList.item(i);
            if (toursNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tourElement = (Element) toursNode;
                String tourID = tourElement.getAttribute("ID");
                String tourname = tourElement.getElementsByTagName("name").item(0).getTextContent();
                String destination = tourElement.getElementsByTagName("destination").item(0).getTextContent();
                int price = Integer.parseInt(tourElement.getElementsByTagName("price").item(0).getTextContent());
                int duration = Integer.parseInt(tourElement.getElementsByTagName("duration").item(0).getTextContent());

                Element guideElement = (Element) tourElement.getElementsByTagName("guide").item(0);
                String guideID = guideElement.getElementsByTagName("id").item(0).getTextContent();
                String name = guideElement.getElementsByTagName("name").item(0).getTextContent();
                String language = guideElement.getElementsByTagName("language").item(0).getTextContent();
                Guide guide = new Guide(guideID, name, language);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                NodeList dateNodeList = ((Element) toursNode).getElementsByTagName("date");
                List<Date> dateList = new ArrayList<>();
                Date date;
                Date dateStart = null;
                Date dateEnd = null;
                for (int j = 0; j < nodeList.getLength(); j++) {
                    String parsedDate = dateNodeList.item(0).getTextContent();
                    String parsedDate2 = dateNodeList.item(1).getTextContent();
                    dateStart = dateFormat.parse(parsedDate);
                    dateEnd = dateFormat.parse(parsedDate2);
                    dateList.add(dateStart);
                    dateList.add(dateEnd);
                }
                for (int j = 0; j < dateList.size(); j++) {
                   dateStart = dateList.get(0);
                   dateEnd = dateList.get(1);
                }
                Tour tour = new Tour(tourID, tourname, destination, price, duration, dateStart, dateEnd, guide);
                tours.add(tour);

            }
        }
        System.out.println(tours);
        return tours;
    }
    public List <Guide> parseGuide (String pathFile) throws IOException, SAXException, ParserConfigurationException {
        List<Guide> guides = new ArrayList<>();
        File file = new File(pathFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("guide");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node guideNode = nodeList.item(i);
            System.out.println(nodeList.item(i));
            if (guideNode.getNodeType() == Node.ELEMENT_NODE) {
                Element guideElement = (Element) guideNode;
                String guideID = guideElement.getElementsByTagName("id").item(0).getTextContent();
                String name = guideElement.getElementsByTagName("name").item(0).getTextContent();
                String language = guideElement.getElementsByTagName("language").item(0).getTextContent();
                Guide guide = new Guide(guideID, name, language);
                guides.add(guide);

            }
        }


        return guides;
    }


    public void save(Tour entity) throws IOException {


        File file = new File("C:\\Users\\modar\\OneDrive\\Рабочий стол\\travelAgency.xml");
        file.setWritable(true);
        FileWriter writer = new FileWriter(file);
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
      writer.write("<travelAgency>\r\n");
      writer.write("<tourID" + entity.getTourID() + ">\r\n" );
      writer.write("<name>" + entity.getTourName() + "</name>\r\n");
      writer.write( "<destination>" + entity.getDestination() + "</destination>\r\n");
      writer.write("<price>" + entity.getPrice() + "</price>\r\n");
      writer.write("<dates>\r\n");
      writer.write( "<date>" + entity.getDateStart() + "</date>\r\n");
      writer.write("<date>" + entity.getDateEnd() + "</date>\r\n");
      writer.write( "</dates>\r\n");
      writer.write("<guide>\r\n");
      writer.write("<id>" + entity.getGuide().getGuideID() + "</id>\r\n");
      writer.write("<name>" + entity.getGuide().getName() + "</name>\r\n");
      writer.write("<language>" + entity.getGuide().getName() + "</language>\r\n");
      writer.write("</guide>");
      writer.write(" </tour>");

    }


    public void saveAll(List <Tour> entities) throws IOException {
        for (int i = 0; i < entities.size(); i++) {
             Tour entity = entities.get(i);
             save(entity);
        }
    }


}
