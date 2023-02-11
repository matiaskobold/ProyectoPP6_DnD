package com.matiaskobold.proyectopp6.service;

import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public Home saveHome(Home home){
        return homeRepository.save(home);
    }
/*
    public List<Home> findAll() {
        return homeRepository.findAll();
    }
*/
    public Home saveOrUpdateHome(Home home){
        return homeRepository.findById(
                home.getId())
                .map(newHome -> {
                    newHome.setAddress(home.getAddress());
                    newHome.setCity(home.getCity());
                    newHome.setCountry(home.getCountry());
                    return homeRepository.save(newHome);
                })
                .orElseGet(() -> {
                    home.setId(home.getId());
                    return homeRepository.save(home);
                });
    }

}
