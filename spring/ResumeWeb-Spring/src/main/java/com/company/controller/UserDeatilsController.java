/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author HP
 */

@Controller
@RequestMapping("/user-details")
public class UserDeatilsController {

    @Autowired
    private UserServiceInter userService;

    @RequestMapping(method = RequestMethod.GET)
    public void get(
            @RequestParam(value = "id") String userIdStr,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Id is not specified");
            }

            int userId = Integer.parseInt(userIdStr);
            User u = userService.getById(userId);

            if (u == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("user", u);
            request.getRequestDispatcher("user-details.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();

            try {
                response.sendRedirect("error?msg=" + ex.getMessage());
            } catch (Exception e) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletResponse response) throws IOException {
        response.sendRedirect("users");
    }
}
