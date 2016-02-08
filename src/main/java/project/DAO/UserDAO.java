package project.DAO;

import project.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends ParkObjectDAO{

   // private Statement statement = null;
    private ResultSet resultSet = null;
    private String result = null;

    public void insertIntoDataBase(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT " +
                "INTO users (id,name,nickname,password,role) VALUES (NULL,?,?,?,'forester')")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserRole(User user) throws SQLException {
        String sql = "SELECT role FROM users WHERE nickname = ?";
        String role = "";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getNickname());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            role = resultSet.getString("role");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public boolean searchForNicknameInDB(User user) {
        String sql = "SELECT * FROM users WHERE nickname =" + " '" + user.getNickname() + "'";
        boolean result = true;

        try (Statement statement = getConnection().createStatement()) {
            resultSet = statement.executeQuery(sql);
            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (result);
    }

    public boolean searchForUserInDB(User user) {
        String sql = "SELECT * FROM users WHERE nickname = '" + user.getNickname()
                + "' AND password = '" + user.getPassword() + "'";
        boolean inDB = false;
        try (Statement statement = getConnection().createStatement()) {
            resultSet = statement.executeQuery(sql);
            inDB = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inDB;
    }

    public List<User> getList() {
        List<User> users = new LinkedList<User>();
        try (Statement statement = getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT id,name,nickname,role FROM users ORDER BY id");

            User user;
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setNickname(resultSet.getString("nickname"));
                user.setRole(resultSet.getString("role"));

                users.add(user);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
