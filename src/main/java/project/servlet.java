package project;

import project.DAO.PlantDAO;
import project.model.Plant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(value = "/")
public class servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String name = "";
//
//        try {
//              Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/park", "user", "password");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * from plants");
//            resultSet.next();
//            name = resultSet.getString("name");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        PlantDAO plantDAO = new PlantDAO();
        plantDAO.getConnection();
        List<Plant> plantListJSP = plantDAO.select();

        req.setAttribute("plants", plantListJSP);
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req,resp);
        plantDAO.closeConnection();

//        req.setAttribute("cat", name);
//        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req,resp);
    }
}