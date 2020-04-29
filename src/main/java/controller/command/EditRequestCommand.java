package controller.command;


import model.DAO.DAORequest;
import model.entities.Request;
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

public class EditRequestCommand implements Command {

    final String urlPattern = "editRequest";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        int submitId = Integer.parseInt(request.getParameter("submit_id"));

        String errorString = null;
        Request req = null;
        System.out.println("req id = " + submitId);
        try {
            req = (new DAORequest()).getRequestByID(submitId);
        } catch (DAOException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("request", req);
        System.out.println(req);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/editRequestView.jsp");
        dispatcher.forward(request, response);
    }
}
