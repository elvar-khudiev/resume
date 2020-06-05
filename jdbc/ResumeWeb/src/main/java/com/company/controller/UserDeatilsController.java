package com.company.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author HP
 */
@WebServlet(name = "UserDetailsController", urlPatterns = {"/user-details"})
public class UserDeatilsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Id is not specified !");
            }

            Integer userId = Integer.parseInt(request.getParameter("id"));
            UserDaoInter userDao = Context.instanceUserDao();
            User u = userDao.getById(userId);
            if (u == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("user", u);
            request.getRequestDispatcher("user-details.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
