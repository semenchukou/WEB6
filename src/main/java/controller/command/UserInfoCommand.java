package controller.command;

import model.entities.User;
import utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserInfoCommand implements Command {

    final String urlPattern = "userInfo";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = CookieUtils.getUserNameInCookie(request);
        String time = CookieUtils.getLoginTimeInCookie(request);
        String errorMessage = null;
        User loginedUser = (User) session.getAttribute("loginedUser");

        if (loginedUser == null) {
            username = "Guest";
            time = "Not provided";
            //response.sendRedirect(request.getContextPath() + "?command=login");
            //return;
        }
        if (username == null || time == null) {
            errorMessage = "Allow to save cookies if you want to see login time";
        }
        request.setAttribute("username", username);
        request.setAttribute("time", time);
        request.setAttribute("errorMessage", errorMessage);

        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/userInfoView.jsp");
        dispatcher.forward(request, response);
    }
}