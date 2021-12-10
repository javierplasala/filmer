package com.filmer.controller;

import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;
import com.filmer.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/actors")
@SessionAttributes("peliParaActor") //Para que me siga mostrando la pelicula en el desplegable
public class ActorController {

@Autowired
private IActorService actorService;

//Metodo para desplegar formulario de carga de actores
@GetMapping("/actors-form")

// redirect: por si queremos mandar alguna información
//model: para enviar información del controlador a la vista
// @ ModelAtribute: recibe lo que le estamos mandando desde el redirect del AdminController y le digo que es de tipo película
public String actorsForm(Actor actor, RedirectAttributes redirect,
                         Model model, @ModelAttribute("peliParaActor")Pelicula pelicula){
model.addAttribute("actor", new Actor()); //Le paso el objeto actor al formulario html
model.addAttribute("film", pelicula); //le mando el objeto pelicula recibido del AdminController


    return "admin/actorsForm"; //Me devuelve el html actorsForm
}

@PostMapping("/save")
public String saveActor(Actor actor, RedirectAttributes redirect, Model model,
                        @ModelAttribute("peliParaActor")Pelicula pelicula){

    actorService.save(actor);
    redirect.addFlashAttribute("actorGuardado","Actor guardado con éxito!");

    return "redirect:/actors/actors-form";
}


}
