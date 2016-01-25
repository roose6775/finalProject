package project.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ParkObjectDAO {

    private Connection connection = null;
    private ResultSet resultSet = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return connection = DriverManager.getConnection("jdbc:mysql://localhost/park", "user", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

