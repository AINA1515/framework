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

    void main() {
        UrlMappingDTO a = new UrlMappingDTO("/home", "GET");
        UrlMappingDTO b = new UrlMappingDTO("/home", "GET");

        System.out.println("equals : " + a.equals(b));
        System.out.println("hashCode a : " + a.hashCode());
        System.out.println("hashCode b : " + b.hashCode());

        Map<UrlMappingDTO, String> map = new HashMap<>();
        map.put(a, "premier");
        System.out.println("containsKey b : " + map.containsKey(b));
    }

}