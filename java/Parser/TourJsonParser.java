package Parser;

import Entity.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


public class TourJsonParser {
    private String filePath;
    private final String directory ="src/main/resources";

    public TourJsonParser(String filePath) {
        this.filePath = filePath;
    }

    ObjectMapper mapper = new ObjectMapper();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public Tour parsedTour() {
        File file = new File(filePath);


        mapper.setDateFormat(dateFormat);
        try {
            Tour tour = mapper.readValue(file, Tour.class);
            return tour;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void save (Tour entity) throws IOException {
        mapper.setDateFormat(dateFormat);

        String fileName = "Tour" + entity.getTourID() +"JSON";
        File file = new File(directory, fileName);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, entity);

    }

    public void saveAll (List<Tour> entities) throws IOException {
        for (int i = 0; i< entities.size(); i++){
            Tour entity = entities.get(i);
            save(entity);
        }
    }
}