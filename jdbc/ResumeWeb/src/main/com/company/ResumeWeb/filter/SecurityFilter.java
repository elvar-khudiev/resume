//package main.com.company.resume.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

//@WebFilter(filterName = "SecurityFilter", urlPatterns = {"*"})
//    public class SecurityFilter implements Filter {
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null) {
//            res.sendRedirect("login");
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//}
