package app.controllers;

import annotation.Controller;

@Controller
public class HomeController {

    public String home() {
        return "Bienvenue depuis app-test";
    }
    public String test() {
        return "Test OK";
    }
}