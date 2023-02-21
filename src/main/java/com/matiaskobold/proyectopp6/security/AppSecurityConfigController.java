package com.matiaskobold.proyectopp6.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity  //to enable Spring Security’s web security support and provide the Spring MVC integration
public class AppSecurityConfigController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    //uso el objeto http para setear todas las opciones de seguridad
                .csrf().disable()
                .authorizeRequests()
                //ApiREST paths permitall
                .antMatchers("/characters").permitAll()
                .antMatchers("/character/{id}").permitAll()
                .antMatchers("/character").permitAll()
                .antMatchers("/character/{id}/home").permitAll()
                .antMatchers("/character/{idC}/home/{idH}").permitAll()
                .antMatchers("/clans").permitAll()
                .antMatchers("/clans/{id}").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/homes").permitAll()
                .antMatchers("/home/{id}").permitAll()
                .antMatchers("/clans/{id}/songs").permitAll()
                .antMatchers("/songs/{id}").permitAll()
                .antMatchers("/song/{id}").permitAll()



                //PermitAll without authentication newUsers
                .antMatchers("/newUser").permitAll()
                .antMatchers("/saveUser").permitAll()
                .antMatchers("/login").permitAll() //permits /login (mapped to HomeController.loginPage()) through Spring Sec so everyone can access login page
                .anyRequest().authenticated()   //all others requests should be authenticated
                .and()
                .formLogin()    //le seteo cual es mi formlogin
                .loginPage("/login").permitAll()    //le seteo cual es mi request controller (/login)
                .and()
                .logout().invalidateHttpSession(true)   //para invalidar la sesion despues del logout
                .clearAuthentication(true)  //para clerear cualquier authentication que haya quedado despues del login
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Aca le pongo el link que me llama el logout
                .logoutSuccessUrl("/logout-success").permitAll();   //despues de llamar al logout, le digo que vaya al logout success
        return http.build();
    }

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
