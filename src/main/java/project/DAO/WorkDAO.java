package project.DAO;

import project.dto.ParkObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class WorkDAO extends ParkObjectDAO {

    public void insertIntoDataBase(ParkObject work) {
        try {
            String sql = "INSERT INTO works (id ,name) VALUES (NULL , ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, work.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataBase(ParkObject work) {
        try {
            String sql = "UPDATE works SET name = ? WHERE id =" + work.getId();
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, work.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDataBase(ParkObject work) {
        try {
            String sql = "DELETE FROM works WHERE name = ? ";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, work.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int searchForIdInDB(String workName) {
        String sql = "SELECT id FROM works WHERE name = ? ";
        int id = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, workName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<ParkObject> getList() {
        List<ParkObject> works = new LinkedList<ParkObject>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,name FROM works ORDER BY id");

            ParkObject work;
            while(resultSet.next()){
                work = new ParkObject();
                work.setId(resultSet.getInt("id"));
                work.setName(resultSet.getString("name"));

                works.add(work);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return works;
    }

}
