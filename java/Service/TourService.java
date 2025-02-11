package Service;

import Entity.Guide;
import Entity.Tour;
import Parser.TourCSVParser;
import Parser.TourJsonParser;
import Parser.TourXMLParser;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TourService {

    private TourXMLParser parser = new TourXMLParser();
    private TourJsonParser JSONparser = new TourJsonParser("src/main/resources/TourT001JSON");
    private TourCSVParser CSVparser = new TourCSVParser("src/main/resources/tour.csv", "src/main/resources/guide.csv", "src/main/resources/somFile");

    public HashMap<String, Tour> getAll(String parseName) throws IOException, ParserConfigurationException, ParseException, SAXException, CsvException {
        HashMap<String, Tour> tours = new HashMap<>();

        if (parseName.equals("CSV")) {
            tours = CSVparser.parseAll();
        } else if (parseName.equals("JSON")) {
            Tour tour = JSONparser.parsedTour();
            tours.put(tour.getTourID(), tour);
        }
        return tours;
    }

    public List<Tour> parseAll() throws IOException, ParserConfigurationException, ParseException, SAXException {
        List<Tour> tours = parser.parseAll();
        return tours;
    }

    public void save(String parseName) throws IOException {
        if (parseName.equals("xml")) {
            parser.save(new Tour());
        } else if (parseName.equals("CSV")) {
            CSVparser.save(new Tour());
        } else if (parseName.equals("JSON")) {
            JSONparser.save(new Tour());

        }
    }

    public void saveAll(List<Tour> entities, String parseName) throws IOException {
        if (parseName.equals("xml")) {
            parser.saveAll(entities);
        } else if (parseName.equals("CSV")) {
            CSVparser.saveAll(entities);
        } else if (parseName.equals("JSON")) {
            JSONparser.saveAll(entities);
        }
    }

    public Tour findById(String tourID, String parserName) throws IOException,
            ParserConfigurationException, ParseException, SAXException, CsvException {
        if (parserName.equals("xml")) {
            List<Tour> tours = parseAll();
            for (Tour tour : tours) {
                if (tour.getTourID().equals(tourID)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        } else {
            HashMap<String, Tour> tours = getAll(parserName);
            for (Tour tour : tours.values()) {
                if (tour.getTourID().equals(tourID)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }

        }
        return null;
    }

    public Tour findByDestination(String destination, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException, CsvException {
        if (parserName.equals("xml")) {
            List<Tour> tours = parseAll();
            for (Tour tour : tours) {
                if (tour.getDestination().equals(destination)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        } else {
            HashMap<String, Tour> tours = getAll(parserName);
            for (Tour tour : tours.values()) {
                if (tour.getDestination().equals(destination)) {
                    return tour;
                }
                System.out.println(tour);
            }
        }
        return null;
    }

    public Tour findByDate(Date dateStart, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException, CsvException {
        if (parserName.equals("xml")) {
            List<Tour> tours = parseAll();
            for (Tour tour : tours) {
                if (tour.getDateStart().equals(dateStart)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        }else {
            HashMap<String, Tour> tours = getAll(parserName);
            for (Tour tour : tours.values()) {
                if (tour.getDateStart().equals(dateStart)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        }
        return null;
    }

    public Tour findByDate(Date dateStart, Date dateEnd, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException, CsvException {
        if (parserName.equals("xml")) {
            List<Tour> tours = parseAll();
        for (Tour tour : tours) {
            if (tour.getDateStart().equals(dateStart) && tour.getDateEnd().equals(dateEnd)) {
                return tour;
            }
            System.out.println(tour);
            break;
        }
    } else {
            HashMap<String, Tour> tours = getAll(parserName);
            for (Tour tour : tours.values()) {
                if (tour.getDateStart().equals(dateStart) && tour.getDateEnd().equals(dateEnd)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        }
        return null;
    }

    public Tour findByGuide(Guide guide, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException, CsvException {
        if(parserName.equals("xml")) {
            List<Tour> tours = parseAll();
            for (Tour tour : tours) {
                if (tour.getGuide().equals(guide)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        } else {
            HashMap<String, Tour> tours = getAll(parserName);
            for (Tour tour : tours.values()) {
                if (tour.getGuide().equals(guide)) {
                    return tour;
                }
                System.out.println(tour);
                break;
            }
        }
        return null;
    }


}