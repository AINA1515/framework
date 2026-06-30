package app.controllers;

import java.io.IOException;

import annotation.Controller;
import annotation.UrlMapping;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@Controller
public class HomeController {

    @UrlMapping(url = "/home", method = "GET")
    public void home(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nom = req.getParameter("nom");
        resp.getWriter().println("Bonjour " + nom);
    }

    @UrlMapping(url = "/test")
    public String test() {
        return "Test OK";
    }
}