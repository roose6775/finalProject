package project.DAO;

import project.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends ParkObjectDAO{

    private Statement statement = null;
    private ResultSet resultSet = null;
    private String result = null;

    public void insertIntoDataBase(User user) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT " +
                    "INTO users (id,name,nickname,password,role) VALUES (NULL,?,?,?,NULL)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserRole(User user) throws SQLException {
        String sql = "SELECT role FROM users WHERE nickname = ?";
        String role = "";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
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

    public boolean searchForNicknameInDB(User user) throws SQLException{
        String sql = "SELECT * FROM users WHERE nickname =" + " '" + user.getNickname() + "'";
        boolean result;
        statement = getConnection().createStatement();
        resultSet = statement.executeQuery(sql);
        result = resultSet.next();
        closeConnection();
        return (result);
    }

    public boolean searchForUserInDB(User user) {
        String sql = "SELECT * FROM users WHERE nickname = '" + user.getNickname()
                + "' AND password = '" + user.getPassword() + "'";
        boolean inDB = true;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            inDB = resultSet.next();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inDB;
    }



}
