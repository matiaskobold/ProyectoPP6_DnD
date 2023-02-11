package com.matiaskobold.proyectopp6.repository;

import com.matiaskobold.proyectopp6.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


//Haciendo un CharacterRepository, al extender Spring JPA Repo nos da los metodos CRUD ya hechos.
//Aca puedo usar JPARepository o CRUDRepository. Lei en internet que es mejor extender con CRUDRepo
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByRace(String gender);
    List<Character> findByIdGreaterThan(Long id);


}
