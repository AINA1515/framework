package app.controllers;

import java.io.IOException;
import java.util.List;

import annotation.Controller;
import annotation.Injection;
import annotation.Parameter;
import annotation.UrlMapping;
import annotation.WebApi;
import utils.ModelAndView;
import utils.UploadedFile;
import app.model.UserModel;
import app.model.AdresseModel;
import app.model.FichierModel;
import app.repository.UserRepository;
import app.repository.AdresseRepository;
import app.repository.FichierRepository;

import jakarta.servlet.http.*;

@Controller
public class SessionController {

    @Injection
    private UserRepository userRepository;

    @Injection
    private AdresseRepository adresseRepository;

    @Injection
    private FichierRepository fichierRepository;

}
