package Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;






@Getter

public class Data {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "Ruben";
    private final String password = "1234";



    public void createDataBase(String dataBaseName) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = "CREATE DATABASE" + dataBaseName;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection connectToDataBase(String dataBaseName) throws SQLException {
      String dataUrl = getUrl() + dataBaseName;
       return DriverManager.getConnection(dataUrl, user,password);
    }




}
