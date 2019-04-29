package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LogInServlet extends HttpServlet {
    private static final UserDao userService = new DatabaseUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (userService.contains(login, password)) {
            request.setAttribute("helloLogin", login);
            request.setAttribute("users", userService.getUsers());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("hello.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("badPass", true);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
