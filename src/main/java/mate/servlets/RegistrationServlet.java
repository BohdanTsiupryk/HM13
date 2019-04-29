package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
import mate.exception.ThisLoginIsExistException;
import mate.model.User;

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
            RequestDispatcher rd = request.getRequestDispatcher("inform.jsp");
            try {
                userService.addUser(new User(login, password, email, country));
            } catch (ThisLoginIsExistException e) {
                request.setAttribute("message", "This login already exist");
            }
            request.setAttribute("message", "You successfully registrated");
            rd.forward(request, response);
        }
    }

}
