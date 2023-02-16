package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/*
As you are using Spring Boot web, Jackson dependency is implicit and we do not have to define explicitly.
You can check for Jackson dependency in your pom.xml in the dependency hierarchy tab if using eclipse.

And as you have annotated with @RestController there is no need to do explicit json conversion.
Just return a POJO and jackson serializer will take care of converting to json. It is equivalent to using @ResponseBody when used with @Controller.
Rather than placing @ResponseBody on every controller method we place @RestController instead of vanilla @Controller and @ResponseBody by default is applied on all resources in that controller.
Refer this link: https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-ann-responsebody

 */
@RestController
public class RestClanController {

    @Autowired
    private ClanService clanService;

    @GetMapping("/clans")
    public ResponseEntity<?> getAllClans(){
        return clanService.getAllClans();
    }

    @GetMapping("/clans/{id}")
    public ResponseEntity<Clan> getClanById(@PathVariable("id") Long id) {
        return clanService.getClanById(id);
    }

    @PostMapping("/clans")
    public ResponseEntity<Clan> createClan(@RequestBody @Valid Clan clan)  {
       return clanService.createClan(clan);

    }
    @PutMapping("/clans/{id}")
    public ResponseEntity<Clan> updateClan(@PathVariable(value="id") Long id, @RequestBody @Valid Clan clan){
        return clanService.updateClan(id, clan);

    }

    @DeleteMapping("/clans/{id}")
    public ResponseEntity<String> deleteClanById(@PathVariable("id") Long id) {
        return clanService.deleteById(id);
    }

    }
