package controller.command;


import model.DAO.DAORequest;
import model.entities.User;
import model.exceptions.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeclineRequestCommand implements Command {

    final String urlPattern = "declineRequest";

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
            String errorString = "You need admin privileges to perform this action.";
            request.setAttribute("errorString", errorString);
            new HomeCommand().doGet(request, response, servletContext);
            return;
        } else {
            if(loginedUser.getRole() == User.Role.VISITOR) {
                String errorString = "You need admin privileges to perform this action.";
                request.setAttribute("errorString", errorString);
                new HomeCommand().doGet(request, response, servletContext);
                return;
            }
        }

        String errorString = null;
        String id = request.getParameter("req_id");
        List list = null;
        try {
            if (id != null) {
                new DAORequest().updateRequestStatus(Integer.parseInt(id), 3);
            }
            list = (new DAORequest()).getALlRequests();
        } catch (DAOException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("requestsList", list);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/declineRequest.jsp");
        dispatcher.forward(request, response);
    }
}
