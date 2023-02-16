package com.matiaskobold.proyectopp6.service;

import com.matiaskobold.proyectopp6.exception.ResourceNotFoundException;
import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;
    public List<Home> findAll() {
        return homeRepository.findAll();
    }
    public ResponseEntity<Home> saveHome(Home home){
        Home homeC = homeRepository.save(home);
        return ResponseEntity.status(HttpStatus.CREATED).body(homeC);
    }
/*
    public List<Home> findAll() {
        return homeRepository.findAll();
    }
*/
    public ResponseEntity<Home> updateHome(Home home, Long id){
        Home pHome = homeRepository.findById(id).
                orElseThrow(
                        ()->new ResourceNotFoundException("Home with ID "+id+" not found!"));
        home.setId(pHome.getId());
        homeRepository.save(home);
        return ResponseEntity.ok().body(home);
    }

}
