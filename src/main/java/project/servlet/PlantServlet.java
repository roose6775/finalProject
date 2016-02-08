package project.servlet;

import project.DAO.PlantDAO;
import project.dto.ParkObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/plants")
public class PlantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PlantDAO plantDAO = new PlantDAO();
        plantDAO.getConnection();
        List<ParkObject> plantListJSP = plantDAO.getList();

        req.setAttribute("plants", plantListJSP);
        req.getRequestDispatcher("WEB-INF/jsp/plants.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PlantDAO plantDAO = new PlantDAO();
        ParkObject plant = new ParkObject();
        plant.setName(req.getParameter("plantName"));

        if (req.getParameter("deleteButton") != null) {
            plantDAO.deleteFromDataBase(plant);
        } else {
            if (req.getParameter("id") != null) {
                plant.setId(Integer.parseInt(req.getParameter("id")));
                plantDAO.updateDataBase(plant);
            } else {
                if (plantDAO.searchForIdInDB(plant.getName()) > 0) {
                    req.setAttribute("AlreadyExistsError", "true");
                    req.getRequestDispatcher("WEB-INF/jsp/plants.jsp").forward(req, resp);// no redirect
                } else {
                    plantDAO.insertIntoDataBase(plant);
                }
            }
        }
        resp.sendRedirect("/plants");
    }

}

