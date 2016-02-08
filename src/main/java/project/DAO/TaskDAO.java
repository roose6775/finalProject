package project.DAO;

import project.dto.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TaskDAO extends ParkObjectDAO {

    public void updateDBWithPlant(Task task) {
        String sql = "UPDATE tasks SET plantId = ? WHERE id =" + task.getId();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getPlantId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBWithWork(Task task) {
        String sql = "UPDATE tasks SET workId = ? WHERE id =" + task.getId();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getWorkId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBComp (Task task) {
        String sql = "UPDATE tasks SET completed = ? WHERE id =" + task.getId();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getIsCompleted());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBConf (Task task) {
        String sql = "UPDATE tasks SET confirmed = ? WHERE id =" + task.getId();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getIsConfirmed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBComments (Task task) {
        String sql = "UPDATE tasks SET foresterComment = ?, ownerComment = ?, taskComment = ?" +
                " WHERE id =" + task.getId();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, task.getForesterComment());
            preparedStatement.setString(2, task.getOwnerComment());
            preparedStatement.setString(3, task.getTaskComment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBTreatment (Task task) {
        String sql = "UPDATE tasks SET treatmentId = ? WHERE id = " + task.getId();
        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getTreatId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoDB(Task task){
        String sql = "INSERT INTO tasks(date,plantId,workId,treatmentId,completed,confirmed,foresterComment,ownerComment,taskComment)" +
                " VALUES (CURDATE(),?,?,?,0,0,NULL,NULL,NULL)";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getPlantId());
            preparedStatement.setInt(2, task.getWorkId());
            preparedStatement.setInt(3, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDB(Task task){
        String sql = "DELETE FROM tasks WHERE id = ?";
        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getList() {
        List<Task> tasks = new LinkedList<Task>();
        String sql = "SELECT t.id,t.date,p.name AS plant, w.name AS work, tr.name AS treat, t.taskComment, t.completed, t.confirmed, t.ownerComment, t.foresterComment "
                + "FROM tasks t "
                + "LEFT JOIN plants p ON p.id = t.plantId "
                + "LEFT JOIN treatments tr ON tr.id = t.treatmentId "
                + "LEFT JOIN works w ON w.id = t.workId ORDER BY t.id";

        try (Statement statement = getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            Task task = null;
            while(resultSet.next()){
                task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTaskDate(resultSet.getDate("date"));
                task.setPlantName(resultSet.getString("plant"));
                task.setTreatName(resultSet.getString("treat"));
                task.setWorkName(resultSet.getString("work"));
                task.setIsConfirmed(resultSet.getInt("confirmed"));
                task.setIsCompleted(resultSet.getInt("completed"));
                if (null != resultSet.getString("taskComment")) {
                    task.setTaskComment(resultSet.getString("taskComment"));
                }
                if (null != resultSet.getString("ownerComment")) {
                    task.setOwnerComment(resultSet.getString("ownerComment"));
                }
                if (null != resultSet.getString("foresterComment")) {
                    task.setForesterComment(resultSet.getString("foresterComment"));
                }

                tasks.add(task);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
