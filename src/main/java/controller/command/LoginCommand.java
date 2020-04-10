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

public class LoginCommand implements Command {

    final String urlPattern = "login";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");

        if (loginedUser == null) {
            CookieUtils.deleteUserCookie(response);
        }
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/loginView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        if(request.getParameter("bt").equals("Guest")) {
            System.out.println("i'm guest");
            HttpSession session = request.getSession();
            CookieUtils.deleteUserCookie(response);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/view/homeView.jsp");
            dispatcher.forward(request, response);
            //response.sendRedirect(request.getContextPath() + "?command=home");
            return;
        }
        if(request.getParameter("bt").equals("Register")) {
            System.out.println("want to register");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/view/registerView.jsp");
            dispatcher.forward(request, response);
            //response.sendRedirect(request.getContextPath() + "?command=home");
            return;
        }

        System.out.println("username = " + userName + " " + password);
        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() < 2 || password.length() < 3) {
            hasError = true;
            errorString = "Required correct username and password!";
            System.out.println(errorString);
        } else {
            try {
                user = new UserDAO().getUser(userName, password);
                System.out.println(user);
                System.out.println("here");
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                    System.out.println("here2");
                }
            } catch (DAOException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/loginView.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginedUser", user);
            if (remember) {
                CookieUtils.storeUserCookie(response, user);
            } else {
                CookieUtils.deleteUserCookie(response);
            }
            System.out.println("posting");
            response.sendRedirect(request.getContextPath() + "?command=userInfo");
        }
    }

}
