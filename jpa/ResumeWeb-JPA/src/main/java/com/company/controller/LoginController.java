package com.company.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AdminDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Admin;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "com.company.controller.LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private UserDaoInter adminDao = Context.instanceUserDao();
    private BCrypt.Verifyer verifyer = BCrypt.verifyer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = adminDao.getByEmail(email);

            System.out.println(user);

            if (user == null) {
                throw new IllegalArgumentException("User doesn't exist !");
            }

            BCrypt.Result rs = verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
            if (!rs.verified) {
                throw new IllegalArgumentException("Password is incorrect !");
            }

            if(user.getAuthorityId().getName().equals("ADMIN")) {
                request.getSession().setAttribute("admin", "admin");
            }

            request.getSession().setAttribute("user", "user");
            response.sendRedirect("users");
        } catch (Exception ex) {
            // setAttribute "login or password is incorrect"
            response.sendRedirect("login");
        }
    }
}