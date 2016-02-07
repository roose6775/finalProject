package project.servlet;

import project.DAO.UserDAO;
import project.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        RequestDispatcher requestDispatcher;
        PrintWriter out;

        if (!(req.getParameter("regName").isEmpty())){
            regUser.setName(req.getParameter("regName"));
        }
        regUser.setNickname(req.getParameter("regNickname"));
        regUser.setPassword(req.getParameter("regPassword"));

        try {

            if (userDAO.searchForNicknameInDB(regUser)) {
                requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/registration.jsp"); // into method
                out = resp.getWriter();
                out.println("<p style='color:red;text-align:center'>That name has already been claimed</p>");
                requestDispatcher.include(req, resp);
            } else if (regUser.getNickname().isEmpty() || regUser.getPassword().isEmpty()
                    || req.getParameter("regPasswordAgain").isEmpty()) {
                requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
                out = resp.getWriter();
                out.println("<p style='color:red;text-align:center'> Required fields can not be empty." +
                        " Fill all the required fields.</p>");
                requestDispatcher.include(req, resp);

            } else if (!(regUser.getPassword().equals(req.getParameter("regPasswordAgain")))) {
                requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
                out = resp.getWriter();
                out.println("<p style='color:red;text-align:center'>Passwords don't match</p>");
                requestDispatcher.include(req, resp);

            } else {
                userDAO.insertIntoDataBase(regUser);
                requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                out = resp.getWriter();
                out.println("<p style='color:green;text-align:center'>Registration is successful! </p>");
                requestDispatcher.include(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
