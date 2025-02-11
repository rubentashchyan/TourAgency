import Controller.Controller;
import Entity.Client;
import Entity.Guide;
import Entity.Tour;
import com.opencsv.exceptions.CsvException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Controller controller = new Controller();

    public void start() throws ParserConfigurationException, IOException,
            ParseException, SAXException, CsvException {

        System.out.println(" добро пожаловать в Меню");

        Scanner scanner = new Scanner(System.in);

        System.out.println("выберите сущность");
        String entity = scanner.nextLine();
        while (true) {
            if (entity.equals("client")) {

                System.out.println("выберите действие:");
                System.out.println("1 - создать спискок клиентов");
                System.out.println("2 - сохранить клиента");
                System.out.println("3 - сохранить сприсок клиентов");
                System.out.println("4 - найти клиента по имени");
                System.out.println("5 - найти клиента оп ID");
                System.out.println("6 - найти клиента по номеру телефона");
                System.out.println("7 - найти клиента по почте");
                String comand = scanner.nextLine();
                if (comand.contains("1")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    String formatName = scanner.nextLine();
                    if (formatName.equals("xml")) {
                        controller.getClientService().parseAll();
                    } else {
                        controller.getClientService().getAll(formatName);
                    }
                }
                if (comand.contains("2")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    String parserName = scanner.nextLine();
                    controller.getClientService().save(parserName);
                }
                if (comand.contains("3")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    String parserName = scanner.nextLine();
                    String patheToFile= scanner.nextLine();

                    //TODO подумать а что с листом
                    controller.getClientService().saveAll(clients, parserName);
                }
                if (comand.contains("4")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите ID");
                    String name = scanner.nextLine();
                    String formatName = scanner.nextLine();
                    controller.getClientService().findByName(name, formatName);

                }
                if (comand.contains("5")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите ID");
                    String ID = scanner.nextLine();
                    String formatName = scanner.nextLine();
                    controller.getClientService().findByID(ID, formatName);
                }
                if (comand.contains("6")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите номер");
                    String phone = scanner.nextLine();
                    String formatName = scanner.nextLine();
                    controller.getClientService().findByPhone(phone, formatName);
                }
                if (comand.contains("7")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите почту");
                    String email = scanner.nextLine();
                    String formatName = scanner.nextLine();
                    controller.getClientService().findByEmail(email, formatName);
                }
            } else if (entity.equals("tour")) {

                System.out.println("выберите действие:");
                System.out.println("1 - создать спискок туров");
                System.out.println("2 - сохранить тур");
                System.out.println("3 - сохранить сприсок туров");
                System.out.println("4 - найти тур по ID");
                System.out.println("5 - найти тур по месту назначения");
                System.out.println("6 - найти тур по дате начала");
                System.out.println("7 - найти тур по дате начала и конца");
                System.out.println("8 - найти тур по гиду");
                String command = scanner.nextLine();
                if (command.contains("1")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    String formatName = scanner.nextLine();
                    if (formatName.equals("xml")) {
                        controller.getTourService().parseAll();
                    } else {
                        controller.getTourService().getAll(formatName);
                    }
                }
                if (command.contains("2")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                     String formatName = scanner.nextLine();
                    controller.getTourService().save(formatName);
                }

                if (command.contains("3")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    List<Tour> entities = new ArrayList<>();
                    String formatName = scanner.nextLine();
                    controller.getTourService().saveAll(entities, formatName);
                }
                if (command.contains("4")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите пункт ID");
                    String ID = scanner.nextLine();
                    String formatName = scanner.nextLine();
                    controller.getTourService().findById(ID, formatName);
                }
                if (command.contains("5")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите пункт назначения");
                    String destination = scanner.nextLine();
                    String formatName = scanner.nextLine();
                    controller.getTourService().findByDestination(destination, formatName);
                }
                if (command.contains("6")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите дату начала");
                    String date = scanner.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat();
                    Date dateStart = dateFormat.parse(date);
                    String formatName = scanner.nextLine();
                    controller.getTourService().findByDate(dateStart, formatName);
                }
                if (command.contains("7")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите дату начала и дату конца");
                    String date1 = scanner.nextLine();
                    String date2 = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat();
                    Date dateStart = dateFormat.parse(date1);
                    Date dateEnd = dateFormat.parse(date2);
                    String formatName = scanner.nextLine();
                    controller.getTourService().findByDate(dateStart, dateEnd, formatName);
                }
                if (command.contains("8")) {
                    System.out.println("выберите формат файла");
                    System.out.println("доступные форматы: xml, CSV, JSON");
                    System.out.println("введите ID, имя и язык гида");
                    Guide guide = new Guide();
                    guide.setGuideID(scanner.nextLine());
                    guide.setName(scanner.nextLine());
                    guide.setLanguage(scanner.nextLine());
                    String formatName = scanner.nextLine();
                    controller.getTourService().findByGuide(guide, formatName);
                }


            }
        }
    }
}