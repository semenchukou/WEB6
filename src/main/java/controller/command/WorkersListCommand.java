package controller.command;


import model.DAO.DAOWorker;
import model.entities.Worker;
import model.exceptions.DAOException;

import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WorkersListCommand implements Command {

    final String urlPattern = "workersList";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        String errorString = null;
        String profile = request.getParameter("profile");
        List list = null;
        try {
            if(profile == null) {
                list = (new DAOWorker()).getAllWorkers();
            } else {
                list = (new DAOWorker()).getWorkerByProfile(profile);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("workersList", list);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/workersView.jsp");
        dispatcher.forward(request, response);
    }
}
