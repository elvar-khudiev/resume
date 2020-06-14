package com.company.controller;

import com.company.entity.User;
import com.company.form.UserForm;
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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceInter userService;

    /* Spring tags */
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView users(
            @Valid
            @ModelAttribute("user") UserForm u,
            BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("users");
        if (bindingResult.hasErrors()) {
            return mv;
        }

        List<User> list = userService.getAll(u.getName(), u.getSurname(), u.getNationalityId());
        mv.addObject("list", list);
        return mv;
    }

    /* HTML tags */
//    @RequestMapping(method = RequestMethod.GET, value = "/users")
//    public ModelAndView users(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "surname", required = false) String surname,
//            @RequestParam(value = "nid", required = false) Integer nationalityId) {
//
//        List<User> list = userService.getAll(name, surname, nationalityId);
//
//        ModelAndView mv = new ModelAndView("users");
//        mv.addObject("list", list);
//
//        return mv;
//    }

    @ModelAttribute("user")
    public UserForm getEmptyUserForm() {
//        if (true) {
//            throw new MyException("ay daaa xeta bash verdi !");
//        }
        return new UserForm(null, null, null);
    }
}