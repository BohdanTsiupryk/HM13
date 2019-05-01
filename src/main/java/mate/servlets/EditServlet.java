package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
import mate.enums.Role;
import mate.model.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(EditServlet.class);
    private static final UserDao userService = new DatabaseUserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("edit");
        User user = userService.getUser(login).get();
        User userSesion = (User) request.getSession().getAttribute("user");
        log.debug("Get user with login: " + user.getLogin());

        request.setAttribute("user", user);
        request.setAttribute("editorRole", userSesion.getRole().getName());
        log.debug("Send user:" + user.getLogin() +" to view");

        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String id = req.getParameter("id");
        String role = req.getParameter("role") == null ? Role.USER.getName() : req.getParameter("role");

        if (userService.updateUser(new User(Integer.valueOf(id), login, password,
                email, country, Role.valueOf(role)))) {
            log.debug("User with id: " + id + ", change information");
        }

        List<User> users = userService.getUsers();
        log.debug("Get users, count: " + users.size());
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("adminPage.jsp");
        rd.forward(req, resp);
    }
}
