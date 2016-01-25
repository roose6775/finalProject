package project.DAO;

import project.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends ParkObjectDAO{

    private Statement statement = null;
    private ResultSet resultSet = null;

    public void insertIntoDataBase(User user) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT " +
                    "INTO users (id,name,nickname,password) VALUES (NULL , ?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean searchForNicknameInDB(User user) throws SQLException {

        boolean result = true; // if database throws exception - user won't be able to register?

        statement = getConnection().createStatement();
        resultSet = statement.executeQuery("SELECT * FROM users WHERE nickname =" +
                " '" + user.getNickname() + "'");
        return (resultSet.next());

    }

    public boolean searchForUserInDB(User user) throws SQLException {
        statement = getConnection().createStatement();
        resultSet = statement.executeQuery("SELECT * FROM users WHERE nickname = '" + user.getNickname()
                + "' AND password = '" + user.getPassword() + "'");
        return (resultSet.next());
    }



}
