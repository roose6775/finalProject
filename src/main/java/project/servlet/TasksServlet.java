package project.servlet;

import project.DAO.PlantDAO;
import project.DAO.TaskDAO;
import project.model.ParkObject;
import project.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/tasks")
public class TasksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session=req.getSession(false);
//        String userName = (String) session.getAttribute("userName");
//        if(userName != null) {
         // req.getRequestDispatcher("WEB-INF/jsp/tasks.jsp").include(req, resp);
//        } else {
//            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").include(req, resp);
//        }

        TaskDAO taskDAO = new TaskDAO();
        taskDAO.getConnection();

        PlantDAO plantDAO = new PlantDAO();
        List<ParkObject> plantList = plantDAO.getList();

        List<Task> taskList = taskDAO.getList();

        req.setAttribute("taskList", taskList);
        req.setAttribute("plantList", plantList);
        req.getRequestDispatcher("WEB-INF/jsp/task.jsp").forward(req,resp);

        taskDAO.closeConnection();

    }
}
