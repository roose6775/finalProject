package project.DAO;

import project.model.ParkObject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PlantDAO extends ParkObjectDAO {

    public void insert(ParkObject plant) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO plants (id ,name) VALUES (NULL , ?)");
            preparedStatement.setString(1, plant.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ParkObject> getList() {
        List<ParkObject> plants = new LinkedList<ParkObject>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM plants");

            ParkObject plant = null;
            while(resultSet.next()){
                plant = new ParkObject();
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
}

