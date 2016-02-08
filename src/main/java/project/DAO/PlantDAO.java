package project.DAO;

import project.dto.ParkObject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PlantDAO extends ParkObjectDAO {


    public void insertIntoDataBase(ParkObject plant) {
        String sql = "INSERT INTO plants (id ,name) VALUES (NULL , ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, plant.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataBase(ParkObject plant) {
        String sql = "UPDATE plants SET name = ? WHERE id =" + plant.getId();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, plant.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDataBase(ParkObject plant) {
        String sql = "DELETE FROM plants WHERE name = ? ";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, plant.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int searchForIdInDB(String plantName) {
        String sql = "SELECT id FROM plants WHERE name = ? ";
        int id = -1;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, plantName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<ParkObject> getList() {
        List<ParkObject> plants = new LinkedList<ParkObject>();
        try (Statement statement = getConnection().createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT id,name FROM plants ORDER BY id");

            ParkObject plant;
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

