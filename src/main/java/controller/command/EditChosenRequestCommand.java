package controller.command;


import model.DAO.DAORequest;
import model.entities.Request;
import model.exceptions.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditChosenRequestCommand implements Command {

    final String urlPattern = "editChosenRequest";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        if(request.getParameter("bt").equals("Save")) {
            String req_id = request.getParameter("request_id");
            String job_type = request.getParameter("job_type");
            String job_descr = request.getParameter("job_descr");
            String to_date = request.getParameter("to_date");
            String tenant_id = request.getParameter("tenant_id");
            String status = request.getParameter("status");
            Request r = new Request(Integer.parseInt(req_id), job_type, job_descr, to_date, Integer.parseInt(tenant_id), Integer.parseInt(status));
            try {
                new DAORequest().updateRequest(r, Integer.parseInt(req_id));
            } catch (DAOException e) {
                e.printStackTrace();
            }
            new RequestsListWithTagCommand().doGet(request, response, servletContext);
        } else {
            new RequestsListWithTagCommand().doGet(request, response, servletContext);
        }

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/editRequestView.jsp");
        dispatcher.forward(request, response);
    }
}
