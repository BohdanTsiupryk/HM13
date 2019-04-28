package mate.servlets;

import mate.servlets.dao.DatabaseUserDao;
import mate.servlets.dao.UserDao;
import mate.servlets.model.User;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    private static final UserDao userService = new DatabaseUserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");

        userService.deleteUser(Integer.valueOf(delete));
        List<User> users = userService.getUsers();
        request.setAttribute("users", users);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("hello.jsp");
        requestDispatcher.forward(request, response);
    }
}
