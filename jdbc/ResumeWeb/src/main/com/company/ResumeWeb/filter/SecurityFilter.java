package main.com.company.ResumeWeb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"*"})
public class SecurityFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println(req.getRequestURI());

        if (req.getSession().getAttribute("user") == null &&
                !req.getRequestURI().equals("/ResumeWeb/login") &&
                !req.getRequestURI().contains("assets/")) {
            res.sendRedirect("login");
        } else if (req.getSession().getAttribute("admin") == null &&
                !req.getRequestURI().equals("/ResumeWeb/users") &&
                !req.getRequestURI().equals("/ResumeWeb/login") &&
                !req.getRequestURI().equals("/ResumeWeb/user-details") &&
                !req.getRequestURI().contains("assets/")) {
            res.sendRedirect("login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
