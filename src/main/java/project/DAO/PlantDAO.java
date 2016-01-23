package project.DAO;

import project.model.Plant;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PlantDAO {

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

    public void insert(Plant plant) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO plants (id ,name) VALUES (NULL , ?)");
            preparedStatement.setString(1, plant.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plant> select() {
        List<Plant> plants = new LinkedList<Plant>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM plants");

            Plant plant = null;
            while(resultSet.next()){
                plant = new Plant();
                plant.setId(resultSet.getInt("id"));
                plant.setName(resultSet.getString("name"));

                plants.add(plant);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }

//    public ResultSet select() {
//        try {
//           Statement statement = connection.createStatement();
//           resultSet = statement.executeQuery("SELECT * FROM plants");
//        } catch (SQLException e) {
//             e.printStackTrace();
//        }
//        return resultSet;
//    }


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

