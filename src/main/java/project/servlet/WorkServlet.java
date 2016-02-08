package project.servlet;

import project.DAO.WorkDAO;
import project.dto.ParkObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/works")
public class WorkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WorkDAO workDAO = new WorkDAO();
        workDAO.getConnection();
        List<ParkObject> workListJSP = workDAO.getList();

        req.setAttribute("works", workListJSP);
        req.getRequestDispatcher("WEB-INF/jsp/works.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WorkDAO workDAO = new WorkDAO();
        ParkObject work = new ParkObject();
        work.setName(req.getParameter("workName"));

        if (req.getParameter("deleteButton") != null) {
            workDAO.deleteFromDataBase(work);
        } else {
            if (req.getParameter("id") != null) {
                work.setId(Integer.parseInt(req.getParameter("id")));
                workDAO.updateDataBase(work);
            } else {
                if (workDAO.searchForIdInDB(work.getName()) > 0) {
                    req.setAttribute("AlreadyExistsError", "true");
                    req.getRequestDispatcher("WEB-INF/jsp/works.jsp").forward(req, resp);
                } else {
                    workDAO.insertIntoDataBase(work);
                }
            }
        }
        resp.sendRedirect("/works");
    }

}
