package project.DAO;

import project.dto.ParkObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TreatmentDAO extends ParkObjectDAO {

    public void insertIntoDataBase(ParkObject treatment) {
        try {
            String sql = "INSERT INTO treatments (id ,name) VALUES (NULL , ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, treatment.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataBase(ParkObject treatment) {
        try {
            String sql = "UPDATE treatments SET name = ? WHERE id =" + treatment.getId();
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, treatment.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDataBase(ParkObject treatment) {
        try {
            String sql = "DELETE FROM treatments WHERE name = ? ";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, treatment.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int searchForIdInDB(String treatmentName) {
        String sql = "SELECT id FROM treatments WHERE name = ? ";
        int id = -1;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, treatmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<ParkObject> getList() {
        List<ParkObject> treatments = new LinkedList<ParkObject>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,name FROM treatments ORDER BY id");

            ParkObject treatment;
            while(resultSet.next()){
                treatment = new ParkObject();
                treatment.setId(resultSet.getInt("id"));
                treatment.setName(resultSet.getString("name"));

                treatments.add(treatment);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }
}


