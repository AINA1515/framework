package app.controllers;

import annotation.Controller;
import annotation.UrlMapping;

@Controller
public class HomeController {

    @UrlMapping(url = "/home")
    public String home() {
        return "Bienvenue depuis app-test";
    }

    @UrlMapping(url = "/test")
    public String test() {
        return "Test OK";
    }
}