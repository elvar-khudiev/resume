package main.com.company.ResumeWeb.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersController", urlPatterns = {"/users"})
public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDaoInter userDao = Context.instanceUserDao();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        String nationalityIdStr = request.getParameter("nid");
        Integer nationalityId = null;
        if (nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
            nationalityId = Integer.parseInt(nationalityIdStr);
        }

        List<User> users = userDao.getAll(name, surname, nationalityId);

        request.setAttribute("users", users);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("action").equals("logout")) {

            request.getSession().removeAttribute("admin");
            request.getSession().removeAttribute("user");

            response.sendRedirect("login");
        } else if (request.getParameter("action").equals("signin")) {
            response.sendRedirect("login");
        }
    }
}