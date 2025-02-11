package Parser;

import Entity.Client;
import com.opencsv.exceptions.CsvException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface AgencyFileHandler <T> {
//    public HashMap<String, T> parseAll() throws ParserConfigurationException, IOException, SAXException, ParseException, CsvException
//
//
//    public List<T> parseAll();
    public void save (T entity) throws IOException, ParserConfigurationException, ParseException, SAXException;
    public void saveAll(List <T> entities) throws IOException, ParserConfigurationException, ParseException, SAXException;
}
