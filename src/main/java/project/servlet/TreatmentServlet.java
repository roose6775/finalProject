package project.servlet;

import project.DAO.TreatmentDAO;
import project.dto.ParkObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(value = "/treatments")
public class TreatmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TreatmentDAO treatmentDAO = new TreatmentDAO();
        treatmentDAO.getConnection();
        List<ParkObject> treatmentListJSP = treatmentDAO.getList();

        req.setAttribute("treatments", treatmentListJSP);
        req.getRequestDispatcher("WEB-INF/jsp/treatments.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TreatmentDAO treatmentDAO = new TreatmentDAO();
        ParkObject treatment = new ParkObject();
        treatment.setName(req.getParameter("treatmentName"));

        if (req.getParameter("deleteButton") != null) {
            treatmentDAO.deleteFromDataBase(treatment);
        } else {
            if (req.getParameter("id") != null) {
                treatment.setId(Integer.parseInt(req.getParameter("id")));
                treatmentDAO.updateDataBase(treatment);
            } else {
                if (treatmentDAO.searchForIdInDB(treatment.getName()) > 0) {
                    req.setAttribute("AlreadyExistsError", "true");
                    req.getRequestDispatcher("WEB-INF/jsp/treatments.jsp").forward(req, resp);// no redirect
                } else {
                    treatmentDAO.insertIntoDataBase(treatment);
                }
            }
        }
        resp.sendRedirect("/treatments");
    }


}
