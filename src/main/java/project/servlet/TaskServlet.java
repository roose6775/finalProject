package project.servlet;

import project.DAO.TaskDAO;
import project.dto.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//todo 1) add treatments 2) add lang 3) add privileges

@WebServlet("/task")
public class TaskServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("goToTask")!=null) {
            String numberOfTask = req.getParameter("goToTask");
            req.setAttribute("numOfTask", numberOfTask);

            TaskDAO taskDAO = new TaskDAO();
            List<Task> taskList = taskDAO.getList();

            for (Task task : taskList) {
                if (task.getId() == Integer.parseInt(numberOfTask)) {
                    req.setAttribute("taskCom", task.getTaskComment());
                    req.setAttribute("isConf", task.getIsConfirmed());
                    req.setAttribute("isComp", task.getIsCompleted());
                    req.setAttribute("forestCom", task.getForesterComment());
                    req.setAttribute("ownCom", task.getOwnerComment());
                    req.setAttribute("plantName",task.getPlantName());
                    req.setAttribute("workName",task.getWorkName());
                    break;
                }
            }
            req.getRequestDispatcher("WEB-INF/jsp/task.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Task task = new Task();
        TaskDAO taskDAO = new TaskDAO();
        String comBox = req.getParameter("compBox");
        String confBox = req.getParameter("confBox");

        task.setId(Integer.parseInt(req.getParameter("numOfTask")));
        if (comBox != null) {
            task.setIsCompleted(1);
        }
        if (confBox != null) {
            task.setIsConfirmed(1);
        }
        taskDAO.updateDBComp(task);
        taskDAO.updateDBConf(task);
        if (req.getParameter("forCom")!=null || req.getParameter("ownCom")!=null
                || req.getParameter("taskCom")!=null){
            task.setForesterComment(req.getParameter("forCom"));
            task.setOwnerComment(req.getParameter("ownCom"));
            task.setTaskComment(req.getParameter("taskCom"));
            taskDAO.updateDBComments(task);
        }
        resp.sendRedirect("/task?goToTask="+task.getId());
    }
}
