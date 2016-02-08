package project.DAO;

import project.CP.Pool;

import java.sql.Connection;

public class ParkObjectDAO {

    private Connection connection;

    public Connection getConnection() {
        try( Connection connection = Pool.getInstance().getConnection()){
            this.connection = connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}

