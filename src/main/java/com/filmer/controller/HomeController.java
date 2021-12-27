package com.filmer.controller;

import com.filmer.entities.Pelicula;
import com.filmer.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    IPeliculasService peliculasService;

    @GetMapping("/")
    //Objeto Model se usa para mandarle info del Controlador a la Vista
    public String home(Model model, Authentication auth) {

        if (auth != null){
            String username = auth.getName();
            model.addAttribute("username", username);
        }

        model.addAttribute("peliculas", peliculasService.listadoPeliculas());
        model.addAttribute("pelicula", new Pelicula());

        return "home";
    }
    @GetMapping("/buscar")
    public String buscarPelicula(@RequestParam String titulo, @ModelAttribute("pelicula") Pelicula pelicula,
                                    Model model){
        Pelicula peli = peliculasService.peliPorTitulo(titulo);

        if (titulo != null){
           model.addAttribute("peli", peli);
        }

        if (peli == null){
            model.addAttribute("peliNoEncontrada", "Sin Resulatados...");
        }

        return "peliBuscador";
    }
}
