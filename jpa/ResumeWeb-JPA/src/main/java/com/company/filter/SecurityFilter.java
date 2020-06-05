package com.company.filter;

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

        if (req.getSession().getAttribute("user") == null &&
                !req.getRequestURI().equals("/ResumeWeb-JPA/login") &&
                !req.getRequestURI().contains("assets/")) {
            res.sendRedirect("login");
        } else if (req.getSession().getAttribute("admin") == null &&
                !req.getRequestURI().equals("/ResumeWeb-JPA/users") &&
                !req.getRequestURI().equals("/ResumeWeb-JPA/login") &&
                !req.getRequestURI().equals("/ResumeWeb-JPA/user-details") &&
                !req.getRequestURI().contains("assets/")) {
            res.sendRedirect("login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
