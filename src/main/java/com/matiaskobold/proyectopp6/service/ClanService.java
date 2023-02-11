package com.matiaskobold.proyectopp6.service;

import com.matiaskobold.proyectopp6.exception.ResourceNotFoundException;
import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;


    public ResponseEntity<?> getAllClans() {
        if (clanRepository.count()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron clanes persistidos");
        }
        else {

            return new ResponseEntity<>(clanRepository.findAll(), HttpStatus.OK);
        }
    }


    public ResponseEntity<Clan> getClanById(Long id) throws ResourceNotFoundException{
        Clan clan = clanRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Clan not found for this id: "+id));
        return ResponseEntity.ok().body(clan);
    }


    public ResponseEntity<Clan> createClan(Clan clan) {
        return new ResponseEntity<>(clanRepository.save(clan), HttpStatus.OK);
    }

    public ResponseEntity<Clan> updateClan(Long id, Clan oldClan) {
        Clan newClan = clanRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Clan not found for this ID:: "+id));
        newClan.setName(oldClan.getName());
        newClan.setDescription(oldClan.getDescription());
        newClan.setMain_language(oldClan.getMain_language());
        newClan.setOperations_base(oldClan.getOperations_base());
        newClan.setTitle(oldClan.getTitle());
        final Clan updatedClan = clanRepository.save(newClan);
        return ResponseEntity.ok(updatedClan);

    }

    public ResponseEntity<HttpStatus> deleteById(Long id) {
        if (clanRepository.existsById(id)){
            clanRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
