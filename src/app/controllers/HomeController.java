package app.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import annotation.Controller;
import annotation.UrlMapping;
import dto.UrlMappingDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import utils.ModelAndView;

@Controller
public class HomeController {


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
}