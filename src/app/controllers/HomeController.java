package app.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import annotation.Controller;
import annotation.UrlMapping;
import dto.UrlMappingDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@Controller
public class HomeController {

    @UrlMapping(url = "/home", method = "GET")
    public void home(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Bonjour ");
    }

    @UrlMapping(url = "/home", method = "GET")
    public void homes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Bonjour ");
    }

    @UrlMapping(url = "/test")
    public String test() {
        return "Test OK";
    }
}