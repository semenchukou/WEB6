package controller.command;

import utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {

    final String urlPattern = "home";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        if (request.getParameter("bt") != null) {
            if (request.getParameter("bt").equals("Login")) {
                if (CookieUtils.getUserNameInCookie(request) != null) {
                    request.setAttribute("errorString", "Log out first.");
                    RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/homeView.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "?command=login");
                }
            } else {
                if (CookieUtils.getUserNameInCookie(request) == null) {
                    request.setAttribute("errorString", "You are not logged in.");
                    RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/homeView.jsp");
                    dispatcher.forward(request, response);
                } else {
                    CookieUtils.deleteUserCookie(response);
                    RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/homeView.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } else {
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/homeView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
