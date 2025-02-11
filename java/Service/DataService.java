package Service;

import Data.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataService {
    private final Data data = new Data();
    // TODO
    // инкапсулировать все statement в класс DATA
    // подключение сделать через Синглтон



    public void initalizeData(String dataBaseName) throws SQLException {
        data.createDataBase(dataBaseName);
        Connection connection = data.connectToDataBase(dataBaseName);
        Statement statement = connection.createStatement();


        String createClientsTable = """
                    CREATE TABLE IF NOT EXISTS clients (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        phone VARCHAR(20) UNIQUE NOT NULL,
                        clientID VARCHAR (20) UNIQUE NOT NULL,
                        tourID VARCHAR(20) NOT NULL,
                    );
                """;
        statement.executeUpdate(createClientsTable);

        String createBookingTable = """
                CREATE TABLE IF NOT EXISTS bookings (
                        id SERIAL PRIMARY KEY,
                        clientID VARCHAR(20) UNQUE NOT NULL,
                        tourID VARCHAR(20) UNQUE NOT NULL,
                        booking_date DATE NOT NULL,
                        );
                """;

        statement.executeUpdate(createBookingTable);

        String createTourTable = """
                CREATE TABLE IF NOT EXISTS tours (
                id SERIAL PRIMARY KEY,
                tourID VARCHAR(20) UNQUE NOT NULL, 
                tourName VARCHAR(20) UNQUE NOT NULL,
                destination VARCHAR(100) NOT NULL,
                price INT(100) NOT NULL,
                duration INT(20) NOT NULL,
                startDate DATE(10) NOT NULL,
                endDATE(10) NOT NULL,
                )
                """;

        statement.executeUpdate(createTourTable);

        String createGuideTable = """
                CREATE TABLE IF NOT EXISTS guides(
                id SERIAL PRIMARY KEY,
                tourID VARCHAR(20) UNQUE NOT NULL,
                name VARCHAR(100) NOT NULL,
                language VARCHAR(20) NOT NULL,
                guideID VARCHAR(20) UNQUE NOT NULL,
                )
                """;
        statement.executeUpdate(createGuideTable);
    }

}
