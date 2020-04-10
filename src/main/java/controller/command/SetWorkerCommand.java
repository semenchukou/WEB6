package controller.command;


import model.DAO.DAORequest;
import model.DAO.DAOWorkPlan;
import model.DAO.DAOWorker;
import model.entities.User;
import model.entities.WorkPlan;
import model.exceptions.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SetWorkerCommand implements Command {

    final String urlPattern = "setWorker";

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
        String request_id = request.getParameter("request_id");
        String worker_id = request.getParameter("worker_id");
        List workers = null;
        List requests = null;
        try {
            DAOWorkPlan dwp = new DAOWorkPlan();
            if (request_id != null && worker_id != null) {
                WorkPlan wp = new WorkPlan(Integer.parseInt(request_id), Integer.parseInt(worker_id));
                dwp.insertPlan(wp);
                new DAORequest().updateRequestStatus(Integer.parseInt(request_id), 1);
            }
            workers = (new DAOWorker()).getAllWorkers();
            requests = (new DAORequest()).getALlRequests();

        } catch (DAOException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("workers", workers);
        request.setAttribute("requests", requests);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/setWorker.jsp");
        dispatcher.forward(request, response);
    }
}
