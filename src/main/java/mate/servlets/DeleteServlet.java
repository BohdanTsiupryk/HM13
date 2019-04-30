package mate.servlets;

import mate.dao.DatabaseUserDao;
import mate.dao.UserDao;
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

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteServlet.class);
    private static final UserDao userService = new DatabaseUserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        Integer deleteId = Integer.valueOf(delete);

        log.debug("Try delete user with id: " + deleteId);
        if (userService.deleteUser(deleteId)) {
            log.debug("Successful deleted id: " + deleteId);
        }

        List<User> users = userService.getUsers();
        log.debug("Get users, count: " + users.size());

        request.setAttribute("users", users);

        request.getRequestDispatcher("adminPage.jsp").forward(request, response);
    }
}
