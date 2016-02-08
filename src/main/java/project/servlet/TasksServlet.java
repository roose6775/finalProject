package project.servlet;

import project.DAO.PlantDAO;
import project.DAO.TaskDAO;
import project.DAO.WorkDAO;
import project.dto.ParkObject;
import project.dto.Task;

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

        TaskDAO taskDAO = new TaskDAO();
        taskDAO.getConnection();

        PlantDAO plantDAO = new PlantDAO();
        List<ParkObject> plantList = plantDAO.getList();

        WorkDAO workDAO = new WorkDAO();
        List<ParkObject> workList = workDAO.getList();

        List<Task> taskList = taskDAO.getList();

        req.setAttribute("taskList", taskList);
        req.setAttribute("plantList", plantList);
        req.setAttribute("workList", workList);
        req.getRequestDispatcher("WEB-INF/jsp/tasks.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PlantDAO plantDAO = new PlantDAO();
        WorkDAO workDAO = new WorkDAO();
        TaskDAO taskDAO = new TaskDAO();
        Task task = new Task();
        String stWork = req.getParameter("work");
        String stPlant = req.getParameter("plant");
        String comBox = req.getParameter("compBox");
        String confBox = req.getParameter("confBox");

        if (req.getParameter("plant") != null) {
            task.setPlantId(plantDAO.searchForIdInDB(stPlant));
        }
        if (req.getParameter("work") != null) {
                task.setWorkId(workDAO.searchForIdInDB(stWork));
            }
        if (req.getParameter("addNewTaskButton") != null) {
            taskDAO.insertIntoDB(task);
        } else {
            task.setId(Integer.parseInt(req.getParameter("taskId")));
            taskDAO.updateDBWithPlant(task);
            taskDAO.updateDBWithWork(task);
            if (comBox != null) {
                task.setIsCompleted(1);
            }
            if (confBox != null) {
                task.setIsConfirmed(1);
            }
            taskDAO.updateDBComp(task);
            taskDAO.updateDBConf(task);
        }
        resp.sendRedirect("/tasks");
    }
}
