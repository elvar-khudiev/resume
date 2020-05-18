package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(method = {RequestMethod.GET},  value = "/login")
    public String login () {
        return "login";
    }

//    @Autowired
//    private UserServiceInter userService;
//
//    private BCrypt.Verifyer verifyer = BCrypt.verifyer();

//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public String login () {
//        return "login";
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public String loginPost (HttpServletRequest request, HttpServletResponse response) throws Exception {
//        try {
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//
//            User user = userService.getByEmail(email);
//
//            System.out.println(user);
//
//            if (user == null) {
//                throw new IllegalArgumentException("User doesn't exist !");
//            }
//
//            BCrypt.Result rs = verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
//            if (!rs.verified) {
//                throw new IllegalArgumentException("Password is incorrect");
//            }
//
//            request.getSession().setAttribute("loggedInUser", user);
//            response.sendRedirect("users");
//        } catch (Exception ex) {
//            response.sendRedirect("login");
//        }
//        return "login";
//    }
}