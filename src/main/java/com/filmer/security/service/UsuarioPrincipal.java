package com.filmer.security.service;

import com.filmer.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//COnseguir que el usuario de la app se asemeje al usuario securizado con el que trabaja SpringSecurity
//UserDetailServiceImp interface de SpringSecurity
public class UsuarioPrincipal implements UserDetails {

    //Tiene las mismas propiedades que tiene la entidad usuario
    private Long id;
    private String username;
    private String password;

    //Creo una Collection de objetos genericos que hereda de GrantedAuthorities
    private Collection<? extends GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public UsuarioPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // ---------------METODOS DE LA CLASE ---------------//

    //Creo un método para convertir el usuario de la app en un usuario de SpringSecurity
    public static UsuarioPrincipal build(Usuario usuario){
        //Creo un listado de las autoridades de Spring
        //busco los roles del usuario.(utilizo fx lambda) para mapear por cada rol que tenga el usuario lo convierte
        // en una autoridad que maneje internamente SpringSecurity(por eso lo convierto en un SimpleGrantedAuthority)
        //(obtengo el rol del nombre).(lo mostramos como String).(transformamos la colletion del map, el String, en un listado)

        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().toString()))
                        .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getId(), usuario.getUsername(), usuario.getPassword(), authorities);
    }

    // -----IMPLEMENTO TODOS LOS METODOS DEL PADRE "USERDETAILS" Y LOS SOBRE ESCRIBO----//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
