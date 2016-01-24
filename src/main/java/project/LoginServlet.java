package project;

import project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        value = "/login",
        loadOnStartup = 1
)
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        User loginUser = new User();

        if (req.getParameter("loginNickname").isEmpty() || req.getParameter("loginPassword").isEmpty()) {
            requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<p style='color:red;text-align:center'> No field can be empty. Fill all the fields.</p>");
            requestDispatcher.include(req, resp);
        } else {
            loginUser.setNickname(req.getParameter("loginNickname"));
            loginUser.setPassword(req.getParameter("loginPassword"));
        }



        System.out.println(loginUser.getNickname());
    }
}

