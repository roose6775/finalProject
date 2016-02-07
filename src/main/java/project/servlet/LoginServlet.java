package project.servlet;

import project.DAO.UserDAO;
import project.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//todo solve the problem with question marks after login with empty fields

@WebServlet(
        value = {"/login"},
        loadOnStartup = 1
)
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = new User();
        RequestDispatcher requestDispatcher;
        PrintWriter out;

        loginUser.setNickname(req.getParameter("loginNickname"));
        loginUser.setPassword(req.getParameter("loginPassword"));

        if (loginUser.getNickname().isEmpty() || loginUser.getPassword().isEmpty()) {
            requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            out = resp.getWriter();
            out.println("<p style='color:red;text-align:center'> No field can be empty. Fill all the fields.</p>");
            requestDispatcher.include(req, resp);
        } else {

            UserDAO userDAO = new UserDAO();
            try {
                if (userDAO.searchForUserInDB(loginUser)) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userName", loginUser.getNickname());
                    session.setAttribute("userRole", userDAO.getUserRole(loginUser));
                    req.getRequestDispatcher("WEB-INF/jsp/startPage.jsp").forward(req, resp);

                } else {
                    requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                    out = resp.getWriter();
                    out.println("<p style='color:red;text-align:center'>Invalid login or password. Try again</p>");
                    requestDispatcher.include(req, resp);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

