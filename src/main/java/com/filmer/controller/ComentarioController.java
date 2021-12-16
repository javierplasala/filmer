package com.filmer.controller;

import com.filmer.entities.Comentario;
import com.filmer.entities.Usuario;
import com.filmer.service.IComentarioService;
import com.filmer.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

@Autowired
    private IComentarioService comentarioService;

@Autowired
    private UsuarioService usuarioService;

@GetMapping("/editar-comentario/{id}")
public String editarComentarioForm(@PathVariable(name="id")Long id, Model model){
    model.addAttribute("comentario", comentarioService.buscarPorId(id));
    return "comentarios/editarComentarioForm";
}
@GetMapping("/editar")
public String editar(@ModelAttribute("comentario")Comentario comentario, Authentication auth,
                     RedirectAttributes redirect){
    String username = auth.getName();
    Optional<Usuario> usuario = usuarioService.getUsuarioByUserName(username);

    comentario.setUsuario(usuario.get());

    comentarioService.saveComentario(comentario);

    redirect.addFlashAttribute("comentarioModificado", "Comentario Modificado!");

    return "redirect:/";
}
@GetMapping("/eliminar-comentario/{id}")
public String eliminar(@PathVariable(name="id")Long id,RedirectAttributes redirect){
    comentarioService.eliminarComentario(id);
    redirect.addFlashAttribute("comentarioEliminado", "Comentario Eliminado!");

    return "redirect:/";
}
}
