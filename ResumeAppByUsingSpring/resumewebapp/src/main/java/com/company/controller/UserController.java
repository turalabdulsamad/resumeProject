package com.company.controller;

import com.company.entity.User;
import com.company.form.UserForm;
import com.company.service.DummyService;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInter userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView index(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              @RequestParam(value = "address", required = false) String address) {

        List<User> list = userService.getAll(name, surname, address);
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", list);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersm")
    public ModelAndView indexM(@Valid @ModelAttribute UserForm u, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("users");

        if(bindingResult.hasErrors()){
            return mv;
        }
        List<User> list = userService.getAll(u.getName(), u.getSurname(), u.getSurname());
        mv.addObject("users", list);
        return mv;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout() {

        return "logout";
    }

    @Autowired
    DummyService dummyService;

    @RequestMapping(method = RequestMethod.GET, value = "/foo")
    public String foo() {
    dummyService.foo();
        return "users";
    }
    @ModelAttribute("user")
    public UserForm getEmptyUserForm() {
        return new UserForm();
    }
}