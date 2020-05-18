package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;

@Controller
public class LogoutController extends HttpServlet {

    @RequestMapping(method = {RequestMethod.GET},  value = "/logout")
    public String logout () {
        return "logout";
    }
}