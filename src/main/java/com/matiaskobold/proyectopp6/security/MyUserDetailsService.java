package com.matiaskobold.proyectopp6.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class MyUserDetailsService implements UserDetailsService {
    //Tengo el userRepo para
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //aca busca el usuario por el username y carga al usuario
        User user =  userRepository.findByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("Usuario no encontrado");
        return new UserDetailsImpl(user);
        //tuve que hacer una clase UserDetailsImpl(user) porque el metodo loaduserbyusername me devuelve userdetails(interfaz)
    }
}
