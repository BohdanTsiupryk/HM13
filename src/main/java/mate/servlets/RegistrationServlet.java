package mate.servlets;

import mate.servlets.dao.InMemoryUserDao;
import mate.servlets.dao.UserDao;
import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserDao userService = new InMemoryUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        PrintWriter writer = response.getWriter();

        if (!password.equals(repassword)) {
            writer.println("Your password and repassword are not the same!");
        } else {
            try {
                userService.addUser(new User(login, password));
            } catch (ThisLoginIsExistException e) {
                writer.println("User with the same login is exist, pls LogIn!");
            }

            writer.print("You successfully registrated, pls LogIn!");
        }
    }
}
