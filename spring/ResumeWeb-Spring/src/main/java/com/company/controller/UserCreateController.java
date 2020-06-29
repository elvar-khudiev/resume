/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.entity.Authority;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.service.inter.CountryServiceInter;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author HP
 */
@Controller
public class UserCreateController {

    @Autowired
    private UserServiceInter userService;

    @Autowired
    private CountryServiceInter countryService;

    @RequestMapping(method = RequestMethod.GET, value = "/user-create")
    public ModelAndView userCreate() {
        ModelAndView mv = new ModelAndView("user-create");
        mv.addObject("user", new User());
        mv.addObject("countries", countryService.getAll());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user-create")
    public String userCreate(
            @ModelAttribute("user") User u,
            @RequestParam(value = "birthPlace", required = false) String birthPlace,
            @RequestParam(value = "birthPlace", required = false) String nationality
            /*@RequestParam(value = "dateStr", required = false) String dateStr*/) {

        System.out.println("---------------------------------");
        System.out.println(u.getName());
        System.out.println(u.getSurname());
        System.out.println(u.getEmail());
        System.out.println(u.getPassword());
        System.out.println(u.getProfileDescription());
        System.out.println(u.getPhone());
        System.out.println(u.getAddress());
        System.out.println(birthPlace);
        System.out.println(nationality);
//        System.out.println(dateStr);
        System.out.println("---------------------------------");

//        java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);
//        u.setBirthdate(sqlDate);

        // String tipinde olan countryleri Country obyektine chevir

        Country c = new Country(1);
        u.setAuthorityId(new Authority(1));
        userService.add(u);
        return "redirect:/users";
    }
}