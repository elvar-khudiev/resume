/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "UserDetailsEditController", urlPatterns = {"/user-details-edit"})
public class UserDetailsEditController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("action = " + request.getParameter("action"));

        String idStr = request.getParameter("id");
        Integer id = null;

        if(idStr != null && !idStr.trim().isEmpty()) {
            id = Integer.parseInt(request.getParameter("id"));
        } else {
            System.out.println("Id is empty !");
        }

        User user = userDao.getById(id);                                         // uygun user bazadan chekilir

        String action = request.getParameter("action");                       // emeliyyatin novu mueyyen olunur

        if (action.equals("update")) {
            String name = request.getParameter("name");                       // label-lerden melumatlar alinir
            String surname = request.getParameter("surname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String profileDescription = request.getParameter("profile-description");
            String address = request.getParameter("address");
            String datepicker = request.getParameter("datepicker");
            String birthPlace = request.getParameter("birthPlace");
            String nationality = request.getParameter("nationality");

            Date sqlDate = Date.valueOf(datepicker);         // String sql.Date-e chevrilir

            CountryDaoInter countryDao = Context.instanceCountryDao();
            List<Country> countries = countryDao.getAll();                       // country ve nationality-ler bazadan chekilir

            for (Country c : countries) {
                if (birthPlace.equals(c.getName())) {                            // label-deki country ve nationality
                    user.setBirthPlace(c);                                       // Db-da movcud birthP. ve natio. lar arasindan sechilir
                }                                                                // ve user-e set edilir

                if (nationality.equals(c.getNationality())) {
                    user.setNationality(c);
                }
            }

            user.setName(name);                                                  // qalan melumatlar user-e set edilir
            user.setSurname(surname);
            user.setPhone(phone);
            user.setEmail(email);
            user.setProfileDescription(profileDescription);
            user.setAddress(address);
            user.setBirthDate(sqlDate);

            userDao.update(user);                        // update olunur, evvelki sehifeye qayidilir
            response.sendRedirect("users");

        } else if (action.equals("back")) {

            response.sendRedirect("users");
        } else if (action.equals("delete")) {

            userDao.delete(id);
            response.sendRedirect("users");
        }
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
            request.getRequestDispatcher("user-details-edit.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
