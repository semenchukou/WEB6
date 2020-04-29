package controller.tags;

import controller.command.HomeCommand;
import model.DAO.DAORequest;
import model.entities.Request;
import model.entities.User;
import model.exceptions.DAOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AllRequestsTag extends TagSupport {
    public int doStartTag() throws JspException {

        HttpSession session = pageContext.getSession();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        ServletContext servletContext = pageContext.getServletContext();
        User loginedUser = (User) session.getAttribute("loginedUser");
        Locale loc = (Locale) session.getAttribute("userLocale");
        ResourceBundle bundle = ResourceBundle.getBundle("locale", loc, this.getClass().getClassLoader());

        if (loginedUser == null) {
            String errorString = "You need to log in to perform this action.";
            request.setAttribute("errorString", errorString);
            try {
                new HomeCommand().doGet(request, response, servletContext);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        String str = "";

        if (loginedUser.getRole() == User.Role.ADMIN) {
            List<Request> list = new LinkedList<>();
            try {
                list = (new DAORequest()).getALlRequests();
            } catch (DAOException e) {
                e.printStackTrace();
            }

            str = "<table width=\"900px\">" +
                    "<thead style=\"color: #007bff;\">" +
                    "<tr>\n" +
                    "<th scope=\"col\">Id</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.job_type") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.job_descr") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.date") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.status") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("bt.edit") + "</th>\n" +
                    "</tr>" +
                    "</thead>\n" +
                    "<tbody>";
            for (int i = 0; i < list.size(); i++) {
                str += "<form action=\"${pageContext.request.contextPath}/?command=editRequest\" method=\"post\">\n" +
                        "<tr>\n" +
                        "<td scope=\"col\">" + list.get(i).getRequest_id() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getJob_type() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getJob_descr() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getTo_date() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getStatus() + "</td>\n" +
                        "<td scope=\"col\"><button>" + bundle.getString("bt.edit") + "</button></td>\n" +
                        "<input type=\"hidden\" name=\"submit_id\" value=\"" + list.get(i).getRequest_id() + "\" />" +
                        "</tr>" +
                        "</form>";
            }
            str += "</tbody>\n" +
                    "</table>";

        } else {
            List<Request> list = new LinkedList<>();
            try {
                list = (new DAORequest()).getALlRequests();
            } catch (DAOException e) {
                e.printStackTrace();
            }

            str = "<table width=\"900px\">" +
                    "<thead style=\"color: #007bff;\"> +" +
                    "<tr>\n" +
                    "<th scope=\"col\">Id</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.job_type") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.job_descr") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.date") + "</th>\n" +
                    "<th scope=\"col\">" + bundle.getString("request.status") + "</th>\n" +
                    "</tr>" +
                    "</thead>\n" +
                    "<tbody>";
            for (int i = 0; i < list.size(); i++) {
                str += "<tr>\n" +
                        "<td scope=\"col\">" + list.get(i).getRequest_id() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getJob_type() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getJob_descr() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getTo_date() + "</td>\n" +
                        "<td scope=\"col\">" + list.get(i).getStatus() + "</td>\n" +
                        "</tr>";
            }
            str += "</tbody>\n" +
                    "</table>";
        }
        System.out.println(str);
        try {
            JspWriter out = pageContext.getOut();
            out.write(str);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
