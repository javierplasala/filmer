
package com.filmer.security;

import com.filmer.config.CustomAccessDeniedHandler;
import com.filmer.security.service.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

//Para habilitar la seguridad
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImp userDetailServiceImp;

    //Inyectamos el método password encoder para poder encriptar las contraseñas
    //Lo inyectamos como bean para tenerlo disponible en todas partes de la app.
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Hace referencia a la clase que nos acabamos de crear para redireccionar al forbidden
    @Bean
    AccessDeniedHandler accessDeniedHandler(){return new CustomAccessDeniedHandler(); }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailServiceImp).passwordEncoder(passwordEncoder());
    }
    //al método userDetailsService le pasamos el userDetailService imp que inyectamos arriba
    // y al passwordEncoder el metodo tb inyectado arriba.

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .antMatchers("/","/error","/forbidden", "Layouts",
                "/login", "/usuario/registro","/usuario/save","/uploads/**","/js/**",
                "/css/**","/peliculas/ver-comentarios/**","/cargar-peli", "/buscar/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll()
                .defaultSuccessUrl("/").failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
    }
    //Ver la config de este metod en video 08 BlogPelis JandroCode min. 35.
    //Le pasamos las rutas que queremos que sean publicas
    //  .anyRequest es decir el resto requiere autenticación
    //loginPage.permitAll.defaultSuccessUrl me redirige al home una vez que estoy logeado
    //y si hay un fallo en el login del usuario se redirigue a login?error.
}