package project.servlet;

import project.DAO.UserDAO;
import project.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User regUser = new User();
        UserDAO userDAO = new UserDAO();

        if (!(req.getParameter("regName").isEmpty())) {
            regUser.setName(req.getParameter("regName"));
        }
        regUser.setNickname(req.getParameter("regNickname"));
        regUser.setPassword(req.getParameter("regPassword"));


        if (userDAO.searchForNicknameInDB(regUser)) {
            req.setAttribute("existedName", "true");
            req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);

        } else if (!(regUser.getPassword().equals(req.getParameter("regPasswordAgain")))) {
            req.setAttribute("dontMatchPasswords", "true");
            req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);

        } else {
            userDAO.insertIntoDataBase(regUser);
            req.setAttribute("successfulRegistration", "true");
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
        }

    }
}
