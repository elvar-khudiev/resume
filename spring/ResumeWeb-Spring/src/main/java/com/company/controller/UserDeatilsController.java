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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 */

@Controller
public class UserDeatilsController {

    @Autowired
    private UserServiceInter userService;

    @RequestMapping(method = RequestMethod.GET, value = "/user-details")
    public ModelAndView userDetails(@RequestParam(value = "id", required = true) Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("Id is not specified");
        }

        User u = userService.getById(id);

        if (u == null) {
            throw new IllegalArgumentException("There is no user with this id");
        }

        ModelAndView mv = new ModelAndView("user-details");
        mv.addObject("user", u);

        return mv;
    }
}
