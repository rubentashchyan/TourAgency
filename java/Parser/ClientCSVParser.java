package Parser;
import Entity.Booking;
import Entity.Client;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ClientCSVParser {

   private String pathToClientCSVFile;
   private String pathToBookingCSVFile;
   private String savedClientFileAdress;



    //TODO переписать Листы на хэшмапы

    public HashMap<String, Client> parseAll() throws IOException, ParseException {
        FileReader clientReader = new FileReader(pathToClientCSVFile);
        FileReader bookingReader = new FileReader(pathToBookingCSVFile);

        HashMap <String,Client> clients = new HashMap<>();
        try (CSVReader CSVreader = new CSVReader(clientReader)) {
           List <String[]> rows =  CSVreader.readAll();
            for (int i = 0; i < rows.size(); i++) {
                String [] row = rows.get(i);
                String clientID = row[0];
                String name = row[1];
                String email = row[2];
                String phone = row [3];
                clients.put(clientID, new Client(clientID, name, email, phone, new ArrayList<>()));
            }

        } catch (CsvException e) {
            throw new RuntimeException(e);
        }


        try(CSVReader CSVBookingReader = new CSVReader(bookingReader)) {
            List <String[]> rows = CSVBookingReader.readAll();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 1; i < rows.size(); i++) {
                String [] row = rows.get(i);

                String clientID = row[0];
                String tourID = row[1];
                Date date = format.parse(row[2]);

               if(clients.containsKey(clientID) == true){
                 Client client = clients.get(clientID);
                 if (client != null) {
                     client.getBookings().add(new Booking(tourID, date));
                 }
               }

            }
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }


    public void save(Client entity) throws IOException, ParserConfigurationException, ParseException, SAXException {


        File savedFile = new File(savedClientFileAdress);
         savedFile.setWritable(true);
         FileWriter fileWriterwriter = new FileWriter(savedFile, true);

        try (CSVWriter writer = new CSVWriter(fileWriterwriter)) {
            String [] header = {"clientID","name", "email", "phone"};
            writer.writeNext(header);

            String [] row = {
                    entity.getClientID(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getPhone(),
            };

            writer.writeNext(row);
        }


    }

    public void saveAll(List <Client> entities) throws IOException, ParserConfigurationException, ParseException, SAXException {
        for (int i = 0; i < entities.size(); i++) {
            Client entity = entities.get(i);
            save(entity);

        }
    }
}
