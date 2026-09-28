package app.controllers;

import annotation.UrlMapping;
import annotation.WebApi;
import app.models.User;

@WebApi
public class UserApi {

    @UrlMapping(url = "/api/hello")
    public String hello() {
        return "Bonjour depuis l'API";
    }

    @UrlMapping(url = "/api/user")
    public User getUser() {
        return new User("Aina", "aina@itu.mg");
    }
}