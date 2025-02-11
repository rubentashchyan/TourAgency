package Controller;

import Entity.Client;
import Parser.ClientCSVParser;
import Parser.ClientJsonParser;
import Parser.ClientXMLParser;
import Parser.TourXMLParser;
import Service.ClientService;
import Service.DataService;
import Service.TourService;
import lombok.Getter;

@Getter
public class Controller {
    private final ClientService clientService = new ClientService();
    private final DataService dataService = new DataService();
    private final TourService tourService = new TourService();
}
