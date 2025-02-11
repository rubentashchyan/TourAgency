package Parser;

import Entity.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ClientJsonParser {
    private String filePath;
    private final String directory = "src/main/resources";




    public Client parsedClient() {

        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
        try {
            Client client =  mapper.readValue(file, Client.class);
            return client;

        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void save(Client client) throws IOException {
        String fileName = "Client" + client.getClientID() + "JSON";
        File file = new File(directory,fileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, client);
    }

    public void saveAll(List<Client> entities) throws IOException {
        for (int i = 0; i < entities.size(); i++) {
            Client client = entities.get(i);
            save(client);

        }

    }
}
