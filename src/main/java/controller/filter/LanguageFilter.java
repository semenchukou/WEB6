package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/*"})
public class LanguageFilter implements Filter {


    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        httpServletRequest.setCharacterEncoding("utf8");

        if (session.getAttribute("userLocale") == null) {
            session.setAttribute("userLocale", httpServletRequest.getLocale());
        }

        String lang = request.getParameter("lang");
        if (lang != null) {
            Locale locale = new Locale(lang);
            session.setAttribute("userLocale", locale);
        }
        chain.doFilter(request, response);
    }

}