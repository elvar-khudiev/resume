package main.com.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AdminDaoInter;
import com.company.entity.Admin;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private AdminDaoInter adminDao = Context.instanceAdminDao();
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

            Admin admin = adminDao.getByEmail(email);

            System.out.println(admin);

            if (admin == null) {
                throw new IllegalArgumentException("User doesn't exist !");
            }

            BCrypt.Result rs = verifyer.verify(password.toCharArray(), admin.getPassword().toCharArray());
            if (!rs.verified) {
                throw new IllegalArgumentException("Password is incorrect !");
            }

            request.getSession().setAttribute("loggedInUser", admin);
            response.sendRedirect("users");
        } catch (Exception ex) {
            // setAttribute "login or password is incorrect"
            response.sendRedirect("login");
        }
    }
}