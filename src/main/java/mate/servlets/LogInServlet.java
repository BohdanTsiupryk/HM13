package mate.servlets;

import mate.servlets.dao.DatabaseUserDao;
import mate.servlets.dao.InMemoryUserDao;
import mate.servlets.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/login")
public class LogInServlet extends HttpServlet {
    private static final UserDao userService = new DatabaseUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();

        if (userService.contains(login, password)) {
            writer.println("Hello " + login);
        } else {
            writer.println("Bad login or password");
        }
    }
}
