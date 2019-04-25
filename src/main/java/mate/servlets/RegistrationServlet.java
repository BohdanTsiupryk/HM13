package mate.servlets;

import mate.servlets.dao.DatabaseUserDao;
import mate.servlets.dao.UserDao;
import mate.servlets.exception.ThisLoginIsExistException;
import mate.servlets.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
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
        PrintWriter writer = response.getWriter();

        if (!password.equals(repassword)) {
            RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
            request.setAttribute("samePass", false);
            rd.forward(request, response);
        } else {
            try {
                userService.addUser(new User(login, password, email, country));
            } catch (ThisLoginIsExistException e) {
                printLogIn(writer, "User with the same login is exist");
            }
            printLogIn(writer, "You successfully registrated");
        }
    }

    private void printLogIn(PrintWriter writer, String message) {
        writer.print("<html> <body>");
        writer.print(message);
        writer.print(", pls <a href=\"login.jsp\">LogIn</a>!");
        writer.print("</body> </html>");
    }
}
