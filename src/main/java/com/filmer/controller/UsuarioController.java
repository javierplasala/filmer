package com.filmer.controller;

import com.filmer.entities.Rol;
import com.filmer.entities.Usuario;
import com.filmer.enums.RolNombre;
import com.filmer.service.UsuarioService;
import com.filmer.service.impl.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    // Vamos a inyectar un servicio de roles para asignarle a cada usuario
    private RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String registrar(){
        return "registro";
    }

    @PostMapping("/save")
    //Metodo para guardar Usuarios con el RolUser
    public String saveUser(String username, String password, RedirectAttributes redirect){

        Usuario usuario = new Usuario();
        usuario.setUsername(username); //Viene del formulario de registro
        usuario.setPassword(passwordEncoder.encode(password)); //encripto la clave

        //Asigno ROL_USER
        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
        //Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();

        //Creo un set para añadir roles porque pueden ser muchos
        Set<Rol> roles = new HashSet<Rol>();
        roles.add(rolUser);
        //roles.add(rolAdmin);

        //Asignamos el rol del usuario al usuario
        usuario.setRoles(roles);

        usuarioService.guardarUsuario(usuario);

        //Utilizamos el model para mandarle info a la vista
        redirect.addFlashAttribute("usuarioRegistrado", "Registro Completado, inicie sesión");

        return"redirect:/login";
    }
    //el password y ussername los recibe

}
