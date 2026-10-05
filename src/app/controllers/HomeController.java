package app.controllers;

import java.io.IOException;

import annotation.Controller;
import annotation.Injection;
import annotation.UrlMapping;
import annotation.WebApi;
import app.model.UserModel;
import app.repository.UserRepository;
import jakarta.servlet.http.*;
import utils.ModelAndView;

@Controller
public class HomeController {

    @Injection
    private UserRepository userRepository;

    @UrlMapping(url = "/home", method = "GET")
    public void homes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Bonjour ");
    }

    @UrlMapping(url = "/test")
    public String test() {
        return "Test OK";
    }

    @UrlMapping(url = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addAttribute("message", "Bonjour depuis le contrôleur !");
        return modelAndView;
    }

    @UrlMapping(url = "/api/hello")
    @WebApi
    public String hello() {
        return "Bonjour depuis l'API";
    }

    @UrlMapping(url = "/api/user")
    @WebApi
    public UserModel getUser() {
        return new UserModel("John Doe", "password123");
    }

    @UrlMapping(url = "/test19", method = "GET")
    public ModelAndView mamo() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addAttribute("message1", userRepository.findById(1L).orElse(null).getUsername());
        return modelAndView;
    }
}