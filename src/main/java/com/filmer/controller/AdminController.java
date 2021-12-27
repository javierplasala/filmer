package com.filmer.controller;

import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;
import com.filmer.service.IActorService;
import com.filmer.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IPeliculasService peliculasService;

    @Autowired
    private IActorService actorService;

    //Metodo para que me despliegue un formulario
    @GetMapping("/peli-form")
    public String peliForm(Model model) { //Para pasarle el objeto que quiero guardar a la base de datos utilizo Model
        model.addAttribute("pelicula", new Pelicula());
        return "admin/peliform";
    }

    //Metodo para guardar la película
    @PostMapping("/save-peli")
    public String savePeli(@RequestParam(name = "file", required = false)MultipartFile portada,
                           Pelicula pelicula, RedirectAttributes redirect){

        //Logica para el guardado de la portada de la peli
        if (!portada.isEmpty()){
            String ruta = "C://Dibus//uploads"; //Almacena la ruta de la imagen de la pelicula
            String nombreUnico = UUID.randomUUID()+" "+ portada.getOriginalFilename(); //Para almacenar el nombre de la portada:
            // utilizo un metod ramdom de uuid que concatena un nro random
            // con el nombre del archivo original para evitar duplicidad o archivos con igual nombre.

            try{
                byte[] bytes = portada.getBytes();
                Path rutaAbsoluta = Paths.get(ruta+"//"+nombreUnico);
                Files.write(rutaAbsoluta,bytes);
                pelicula.setPortada(nombreUnico);

                peliculasService.save(pelicula);
                redirect.addFlashAttribute("peliGuardada",
                        "Peli Guardada con éxito"); //"peliGuardada" es el valor qeu recojo de la vista html5 "peliform" con las etiquetas Thymeleaf
                redirect.addFlashAttribute("peliParaActor", pelicula);
            }catch (Exception e){
                e.getCause().getMessage();
            }
        }
        return "redirect:/actors/actors-form"; //para que me redireccione al formulario de carga de actores
        }

        //MÉTDO PARA DEVOLVER VISTA DE GESTIONADOR DE PELICULAS

    @GetMapping("/gestion-peliculas")
    public String listadoPeliculas(Model model){
        model.addAttribute("peliculas", peliculasService.listadoPeliculas());

        return "admin/gestionPeliculas";
    }

    //MÉTDO PARA ELIMINAR PELICULAS
    @GetMapping("/eliminar-pelicula/{id}")
    public String eliminarPelicula(@PathVariable Long id, RedirectAttributes redirect){
        peliculasService.eliminarPelicula(id);
        redirect.addFlashAttribute("peliEliminada","Pelicula Eliminada");

        return "redirect:/admin/gestion-peliculas";
    }

    @GetMapping("/editar-form/{id}")
    public String editarFormulario(@PathVariable Long id, Model model){
        Pelicula pelicula = null;

        if (id>0){
            pelicula = peliculasService.peliculaPorId(id);
            model.addAttribute("pelicula", pelicula);
        }
        return "admin/editarPelicula";
    }

    @PostMapping("/editar-pelicula")
    public String editarPelicula(@RequestParam(name="file") MultipartFile imagePortada,
                                 Pelicula peli, RedirectAttributes redirect,
                                 @ModelAttribute("pelicula") Pelicula pelicula, Model model) {

        if (!imagePortada.isEmpty()) {
            String ruta = "C://Dibus//uploads";
            String nombreUnico = UUID.randomUUID()+" "+ imagePortada.getOriginalFilename();

            try{
                byte[] bytes = imagePortada.getBytes();
                Path rutaAbsoluta = Paths.get(ruta+"//"+nombreUnico);
                Files.write(rutaAbsoluta,bytes);
                pelicula.setPortada(nombreUnico);

                peliculasService.save(pelicula);
                redirect.addFlashAttribute("peliEditada","Peli Editada con éxito");

                }catch (Exception e){
                e.getCause().getMessage();
                }
        }

        return "redirect:/admin/gestion-peliculas";

    }
    @GetMapping("/editar-actores/{id}")
    public String editarActores(@PathVariable Long id, Model model){

        Pelicula peliculaById = peliculasService.peliculaPorId(id);
        model.addAttribute("peliEncontrada", peliculaById);

        return "admin/edicionActores";
    }

    @GetMapping("/cargar-actor/{id}")
    public String cargarActor(@PathVariable Long id, Model model){

        Actor actor = actorService.obtenerActor(id);
        model.addAttribute("actor", actor);

        return "admin/editarActorForm";
    }

    @PostMapping("/editar-actor")
    public String editarActor(@ModelAttribute("actor")Actor actor, RedirectAttributes redirect){

        actorService.save(actor);
        redirect.addFlashAttribute("actorEditado","Actor Editado con éxito");
        return "redirect:/admin/gestion-peliculas";
    }


    @GetMapping("/eliminar-actor/{id}")
    public String eliminarActor(@PathVariable Long id, RedirectAttributes redirect){

        actorService.eliminarActor(id);
        redirect.addFlashAttribute("actorEliminado","Actor Eliminado de la BD");

        return "redirect:/admin/gestion-peliculas";
    }


}

