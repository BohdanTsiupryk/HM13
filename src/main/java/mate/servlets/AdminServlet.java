package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
import mate.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final UserDao userService = new DatabaseUserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<User> users = userService.getUsers();

        req.setAttribute("user", user);
        req.setAttribute("users", users);

        req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
    }
}
