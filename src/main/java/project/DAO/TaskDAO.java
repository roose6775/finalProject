package project.DAO;

import project.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TaskDAO extends ParkObjectDAO {

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
                task.setIsConfirmed(resultSet.getBoolean("confirmed"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
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
