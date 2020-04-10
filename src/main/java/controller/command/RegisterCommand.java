package controller.command;

import model.DAO.UserDAO;
import model.entities.User;
import model.exceptions.DAOException;
import utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterCommand implements Command {

    final String urlPattern = "register";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() < 2 || password.length() < 3) {
            hasError = true;
            errorString = "Required correct username and password!";
            System.out.println(errorString);
        } else if (!password.equals(repassword)) {
            hasError = true;
            errorString = "Passwords do not match!";
            System.out.println(errorString);
        } else {
            try {
                if (request.getParameter("admin") != null) {
                    user = new User(userName, password, User.Role.ADMIN, 0);
                    new UserDAO().insertUser(user);
                } else {
                    user = new User(userName, password, User.Role.VISITOR, 0);
                    new UserDAO().insertUser(user);
                }
            } catch (DAOException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/loginView.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginedUser", user);

            CookieUtils.storeUserCookie(response, user);

            System.out.println("posting");
            response.sendRedirect(request.getContextPath() + "?command=userInfo");
        }
    }
}
