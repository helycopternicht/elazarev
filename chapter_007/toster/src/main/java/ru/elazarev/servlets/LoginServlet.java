package ru.elazarev.servlets;

import ru.elazarev.filters.AuthorizationFilter;
import ru.elazarev.models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static List<UserModel> users;

    @Override
    public void init() throws ServletException {
        users = new CopyOnWriteArrayList<>();
        users.add(new UserModel(1, "admin", "123", "admin@gmail.com", true));
        users.add(new UserModel(1, "user", "123", "user@gmail.com", false));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Я тута!");

        String login = request.getParameter("login");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession();

        for (UserModel u : users) {
            if (login.equals(u.getLogin()) && pwd.equals(u.getPassword())) {
                session.setAttribute("user", u);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
