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

public class RequestsListWithTagCommand implements Command {

    final String urlPattern = "requestsListWithTag";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/requestsViewWithTag.jsp");
        dispatcher.forward(request, response);
    }
}
