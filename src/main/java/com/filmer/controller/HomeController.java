package com.filmer.controller;

import com.filmer.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    IPeliculasService peliculasService;

    @GetMapping("/")
    //Objeto Model se usa para mandarle info del Controlador a la Vista
    public String home(Model model) {
        model.addAttribute("peliculas", peliculasService.listadoPeliculas());

        return "home";
    }
}
