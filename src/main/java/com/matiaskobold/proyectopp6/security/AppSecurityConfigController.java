package com.matiaskobold.proyectopp6.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity  //to enable Spring Security’s web security support and provide the Spring MVC integration
public class AppSecurityConfigController {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean  //Le pongo @Bean para tener un objeto AuthenticationProvider
    public AuthenticationProvider authenticationProvider() { //En este metodo es de donde saco la lista de usuarios que se pueden logear.
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        //Este userDetailsService se tiene que conectar con mi user en mi model, y aca le pongo los metodos para buscar y guardar usuarios nuevos.
        //A userDetailsService lo implemento en MyUserDetailsService

        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }


}
