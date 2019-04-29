package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
import mate.model.User;

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
    private static final UserDao userService = new DatabaseUserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String edit = request.getParameter("edit");
        User user = userService.getUser(edit).get();

        request.setAttribute("user", user);
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

        userService.updateUser(new User(Integer.valueOf(id), login, password, email, country));
        List<User> users = userService.getUsers();
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("hello.jsp");
        rd.forward(req, resp);
    }
}
