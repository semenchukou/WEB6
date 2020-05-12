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

public class ChatCommand implements Command {

    final String urlPattern = "chat";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("loginedUser");
        if (user == null) {
            String errorString = "You need admin privileges to perform this action.";
            request.setAttribute("errorString", errorString);
            new HomeCommand().doGet(request, response, servletContext);
            return;
        }
        request.setAttribute("senderId", user.getUsername());
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/view/chatView.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            try {
                throw new Exception("Failed page forwarding", e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}