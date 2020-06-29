/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.entity.Country;
import com.company.entity.User;
import com.company.form.UserDetailsForm;
import com.company.form.UserForm;
import com.company.service.inter.CountryServiceInter;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

/**
 * @author HP
 */
@Controller
public class UserDetailsEditController {

    @Autowired
    private UserServiceInter userService;

    @Autowired
    private CountryServiceInter countryService;

    @RequestMapping(method = RequestMethod.GET, value = "/user-details-edit")
    public ModelAndView userDetailsEdit(@RequestParam(value = "id") Integer id) {
        UserDetailsForm user = getForm(userService.getById(id));
        ModelAndView mv = new ModelAndView("user-details-edit");
        mv.addObject("user", user);
        mv.addObject("countries", countryService.getAll());
        mv.addObject("userId", id);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user-details-edit")
    public String userDetailsEdit(
            @ModelAttribute("user") UserDetailsForm u,
            @RequestParam(value = "userId") Integer id,
            @RequestParam(value = "birthPlace", required = false) String birthPlace,
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "dateStr", required = false) String dateStr) {

        User user = userService.getById(id);

        user.setName(u.getName());
        user.setSurname(u.getSurname());
        user.setPhone(u.getPhone());
        user.setEmail(u.getEmail());
        user.setProfileDescription(u.getProfileDescription());
        user.setAddress(u.getAddress());

        userService.update(user);
        return "redirect:/users";
    }

    private UserDetailsForm getForm(User user) {
        return new UserDetailsForm(
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword(),
                user.getProfileDescription());
    }
}
