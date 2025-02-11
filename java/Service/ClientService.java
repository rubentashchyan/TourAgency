package Service;

import Entity.Client;
import Parser.ClientCSVParser;
import Parser.ClientJsonParser;
import Parser.ClientXMLParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;


@Getter
public class ClientService {



    private ClientXMLParser parser = new ClientXMLParser("src/main/resources/TourAgency.xml");
    private ClientCSVParser CSVparser = new ClientCSVParser("src/main/resources/client.csv", "src/main/resources/booking.csv", "src/main/resources/somFile");

    private ClientJsonParser JSONparser = new ClientJsonParser("src/main/resources/ClientC001JSON");


    public HashMap<String, Client> getAll(String parserName) throws ParserConfigurationException, IOException, ParseException, SAXException {
        HashMap<String, Client> clients = new HashMap<>();
        if (parserName.equals("CSV")) {
            clients = CSVparser.parseAll();
        } else if (parserName.equals("JSON")) {
            Client client = JSONparser.parsedClient();
            clients.put(client.getClientID(), client);
        }
        return clients;
    }

    public List<Client> parseAll() throws ParserConfigurationException, IOException, ParseException, SAXException {
        List<Client> clients = parser.parseAll();
        return clients;
    }

    public void save(String parserName) throws IOException, ParserConfigurationException, ParseException, SAXException {
        if (parserName.equals("xml")) {
            parser.save(new Client());
        } else if (parserName.equals("CSV")) {
            CSVparser.save(new Client());
        } else if (parserName.equals("Json")) {
            JSONparser.save(new Client());
        }
    }

    public void saveAll(List<Client> entities, String parserName) throws IOException, ParserConfigurationException, ParseException, SAXException {
        if (parserName.equals("xml")) {
            parser.saveAll(entities);
        } else if (parserName.equals("CSV")) {
            CSVparser.saveAll(entities);
        } else if (parserName.equals("JSON")) {
            JSONparser.saveAll(entities);
        }
    }


    public Client findByName(String name, String parserName) throws IOException,
            ParserConfigurationException, ParseException, SAXException {
        if (parserName.equals("xml")) {
            List<Client> clients = parseAll();
            for (Client client : clients) {
                if (client.getName().equals(name)) {
                    return client;
                }
                System.out.println(client);
                break;
            }
        } else {
            HashMap<String, Client> clients = getAll(parserName);
            for (Client client : clients.values()) {
                if (client.getName().equals(name)) {
                    return client;
                }
                System.out.println(client);
                break;
            }

        }

        return null;
    }


    public Client findByID(String clientID, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException {
        if (parserName.equals("xml")) {
            List<Client> clients = parseAll();
            for (Client client : clients) {
                if (client.getClientID().equals(clientID)) {
                    return client;
                }
                System.out.println(client);
                break;
            }
        } else {
            HashMap<String, Client> clients = getAll(parserName);
            for (Client client : clients.values()) {
                if (client.getClientID().equals(clientID)) {

                    return client;
                }
                System.out.println(client);
                break;
            }
        }
        return null;
    }

    public Client findByPhone(String phone, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException {

        HashMap<String, Client> clients = getAll(parserName);

        for (Client client : clients.values()) {
            if (client.getPhone().equals(phone)) {
                return client;

            }
            System.out.println(client);
            break;
        }
        return null;
    }

    public Client findByEmail(String email, String parserName) throws
            IOException, ParserConfigurationException, ParseException, SAXException {
        if (parserName.equals("xml")) {
            List<Client> clients = parseAll();
            for (Client client : clients) {
                if (client.getEmail().equals(email)) {
                    return client;
                }
                System.out.println(client);
                break;
            }
        } else {
            HashMap<String, Client> clients = getAll(parserName);
            for (Client client : clients.values()) {
                if (client.getEmail().equals(email)) {
                    return client;
                }
                System.out.println(client);
                break;
            }
          }
            return null;

    }


}
