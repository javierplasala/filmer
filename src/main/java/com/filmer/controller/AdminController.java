package com.filmer.controller;

import com.filmer.entities.Pelicula;
import com.filmer.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}

