package project.servlet;

import project.DAO.UserDAO;
import project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        value = {"/login"},
        loadOnStartup = 1
)
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  String loc = "en";
       // req.setAttribute("lang", loc);
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = new User();
        RequestDispatcher requestDispatcher;
        PrintWriter out;

        if (req.getParameter("loginNickname").isEmpty() || req.getParameter("loginPassword").isEmpty()) {
            requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            out = resp.getWriter();
            out.println("<p style='color:red;text-align:center'> No field can be empty. Fill all the fields.</p>");
            requestDispatcher.include(req, resp);
        } else {
            loginUser.setNickname(req.getParameter("loginNickname"));
            loginUser.setPassword(req.getParameter("loginPassword"));

            UserDAO userDAO = new UserDAO();
            String userName = req.getParameter("loginNickname");

            try {
                if (userDAO.searchForUserInDB(loginUser)) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userName", userName);
                    req.getRequestDispatcher("WEB-INF/jsp/loginSuccess.jsp").forward(req, resp);

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

