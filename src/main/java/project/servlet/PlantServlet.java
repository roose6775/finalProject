package project.servlet;

import project.DAO.PlantDAO;
import project.model.ParkObject;

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

      //  LoginFilter filter = new LoginFilter();
      //  filter.doFilter(req,resp,)

       // HttpSession session = req.getSession(false);
       // String userName = (String) session.getAttribute("userName");
      //  System.out.println(userName);

        PlantDAO plantDAO = new PlantDAO();
        plantDAO.getConnection();
        List<ParkObject> plantListJSP = plantDAO.getList();

        req.setAttribute("plants", plantListJSP);
        req.getRequestDispatcher("WEB-INF/jsp/plants.jsp").forward(req,resp);
        plantDAO.closeConnection();

    }
}
