package project.servlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import project.DAO.UserDAO;
import project.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        value = {"/login"},
        loadOnStartup = 1
)
public class LoginServlet extends HttpServlet{

    final static Logger logger = Logger.getLogger(LoginServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = new User();

        PropertyConfigurator.configure("log4j.properties");

        loginUser.setNickname(req.getParameter("loginNickname"));
        loginUser.setPassword(req.getParameter("loginPassword"));

        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.searchForUserInDB(loginUser)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("userName", loginUser.getNickname());
                session.setAttribute("userRole", userDAO.getUserRole(loginUser));
                logger.info("user " + loginUser.getNickname() + " LOGGED IN with " + userDAO.getUserRole(loginUser) + " rights");
                req.getRequestDispatcher("WEB-INF/jsp/startPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("loginError", "true");
                req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
                logger.info("login failed - invalid login/password");
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

}

