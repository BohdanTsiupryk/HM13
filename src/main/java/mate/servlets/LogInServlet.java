package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
import mate.enums.Role;
import mate.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LogInServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogInServlet.class);
    private static final UserDao userService = new DatabaseUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (userService.contains(login, password)) {
            request.setAttribute("helloLogin", login);
            User user = userService.getUser(login).get();
            request.getSession().setAttribute("user", user);

            if (user.getRole().equals(Role.ADMIN)) {
                log.info("Admin " + login + " online");
                request.getRequestDispatcher("/admin").forward(request, response);
            } else if (user.getRole().equals(Role.USER)) {
                log.info("User " + login + " online");
                request.setAttribute("user", user);
                request.getRequestDispatcher("userProfile.jsp").forward(request, response);
            }

            request.getRequestDispatcher("info/accessDenied.jsp").forward(request, response);
        } else {
            log.info("User with login: " + login + ", bad try to authorization!");
            request.setAttribute("badPass", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
