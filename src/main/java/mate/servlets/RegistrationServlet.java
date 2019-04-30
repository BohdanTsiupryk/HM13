package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
import mate.enums.Role;
import mate.exception.ThisLoginIsExistException;
import mate.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RegistrationServlet.class);
    private static final UserDao userService = new DatabaseUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        if (!password.equals(repassword)) {
            log.info("Login: " + login + ", password and repassword not the same");
            request.setAttribute("samePass", false);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            log.info("Try register user with login: " + login);

            try {
                userService.addUser(new User(login, password, email, country, Role.USER));
            } catch (ThisLoginIsExistException e) {
                log.info("Try register user with login: " + login);
                request.setAttribute("message", "This login already exist");
                request.getRequestDispatcher("info/inform.jsp").forward(request, response);
            }

            log.info("Success register user with login: " + login);
            request.setAttribute("successReg", "You successfully registrate");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
