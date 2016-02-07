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
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, task.getPlantId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBWithWork(Task task) {
        String sql = "UPDATE tasks SET workId = ? WHERE id =" + task.getId();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, task.getWorkId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBComp (Task task) {
        String sql = "UPDATE tasks SET completed = ? WHERE id =" + task.getId();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, task.getIsCompleted());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBConf (Task task) {
        String sql = "UPDATE tasks SET confirmed = ? WHERE id =" + task.getId();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, task.getIsConfirmed());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDBComments (Task task) {
        String sql = "UPDATE tasks SET foresterComment = ?, ownerComment = ?, taskComment = ?" +
                " WHERE id =" + task.getId();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, task.getForesterComment());
            preparedStatement.setString(2, task.getOwnerComment());
            preparedStatement.setString(3, task.getTaskComment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoDB(Task task){
        String sql = "INSERT INTO tasks(plantId,workId,completed,confirmed,foresterComment,ownerComment,taskComment)" +
                " VALUES (?,?,NULL,NULL,NULL)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, task.getPlantId());
            preparedStatement.setInt(2, task.getWorkId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getList() {
        List<Task> tasks = new LinkedList<Task>();
        try {
            Statement statement = getConnection().createStatement();
            String sql = "SELECT t.id,p.name AS plant, w.name AS work, t.taskComment, t.completed, t.confirmed, t.ownerComment, t.foresterComment "
                       + "FROM tasks t "
                       + "LEFT JOIN plants p ON p.id = t.plantId "
                       + "LEFT JOIN works w ON w.id = t.workId ORDER BY t.id";
            ResultSet resultSet = statement.executeQuery(sql);

            Task task = null;
            while(resultSet.next()){
                task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setPlantName(resultSet.getString("plant"));
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
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
