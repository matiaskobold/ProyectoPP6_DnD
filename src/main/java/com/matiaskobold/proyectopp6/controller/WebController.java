package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

/*
    @Autowired
    CharacterRepository characterRepository;

    //Apenas entro me manda a la home
    @RequestMapping ("/")
    public String home(){
        System.out.println(characterRepository.findByGender("male"));
        System.out.println(characterRepository.findByIdGreaterThan(1L));
        System.out.println(characterRepository.findByGenderSortedById("male"));
        return "home.html";

    }
    //en la home, hay una form que me manda a /addUser. En el URL estan todos los parametros de Character, y con magia de Spring lo puedo agarrar como un Character character y guardarlo.
    @RequestMapping("/addUser")
    public String addUser(Character character){
        characterRepository.save(character);
        return "objeto_agregado.html";
    }

    //aca le pongo que hay un parametro de la request que es el Long id (viene de la view home en la form de show_user). Con ese parametro hago un nuevo Character...
    //y a ese nuevo user lo meto en el modelo, para despues leerlo con thymeleaf en show_user
    @RequestMapping("/getUser")
    public ModelAndView getUser(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("show_user.html");
        Character character = characterRepository.findById(id).orElse(null);
        mv.addObject(character);
        return mv;

    }

*/


}
