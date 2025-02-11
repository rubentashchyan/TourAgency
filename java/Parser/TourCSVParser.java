package Parser;

import Entity.Guide;
import Entity.Tour;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Data
public class TourCSVParser  {
    private String pathToTourCSVFile;
    private String pathToGuideCSVFile;
    private String savedTourFileAdress;


    // TODO
// переписать в соответствии со струткурой файла или переделать файл

    public HashMap<String, Tour> parseAll() throws ParserConfigurationException, IOException, SAXException, ParseException, CsvException {
        FileReader tourReader = new FileReader(pathToTourCSVFile);
        FileReader guideReader = new FileReader(pathToGuideCSVFile);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        List<Tour> tours = new ArrayList<>();
        HashMap <String, Tour> tours = new HashMap<>();
        CSVReader tourCSVReader = new CSVReader(tourReader);
        CSVReader guideCSVRider = new CSVReader(guideReader);
        List<String[]> guideRows = guideCSVRider.readAll();
//        System.out.println(Arrays.deepToString(new List[]{guideRows}));
        List<String[]> tourRows = tourCSVReader.readAll();
//        System.out.println(Arrays.toString(new List[]{tourRows}));
        String tourID = null;
        String tourName = null;
        String destination = null;
        int price = 0;
        int duration = 0;
        Date dateStart = null;
        Date dateEnd = null;
        for (int i = 1; i < tourRows.size(); i++) {
            String[] row = tourRows.get(i);

            tourID = row[0];
            tourName = row[1];
            destination = row[2];
            price = Integer.parseInt(row[3]);
            duration = Integer.parseInt(row[4]);
            dateStart = format.parse(row[5]);
            dateEnd = format.parse(row[6]);
        }

        for (int i = 0; i < guideRows.size(); i++) {
            String[] row = guideRows.get(i);
            String guideID = row[0];
            String name = row[1];
            String language = row[2];
            tourID = row[3];

            for (Tour tour : tours.values()) {
                tour.setGuide(new Guide(guideID, name, language));

            }

        }

        tours.put(tourID, new Tour(tourID, tourName, destination, price, duration, dateStart, dateEnd, new Guide()));
        System.out.println(tours);
        return tours;

    }



    public void save(Tour entity) throws IOException {
        File savedFile = new File(savedTourFileAdress);
        savedFile.setWritable(true);
        FileWriter fileWriterwriter = new FileWriter(savedFile, true);

        try (CSVWriter writer = new CSVWriter(fileWriterwriter)) {
            String[] header = {"tourID", "tourName", "destination", "destination", "price", "duration", "dateStart", "dateEnd"};
            writer.writeNext(header);

            String[] row = {
                    entity.getTourID(),
                    entity.getTourName(),
                    entity.getDestination(),
                    String.valueOf(entity.getPrice()),
                    String.valueOf(entity.getDuration()),
                    String.valueOf(entity.getDateStart()),
                    String.valueOf(entity.getDateEnd()),

            };

            writer.writeNext(row);
        }

    }



    public void saveAll(List<Tour> entities) throws IOException {
        for (int i = 0; i < entities.size(); i++) {
            Tour entity = entities.get(i);
            save(entity);
        }

    }


}
