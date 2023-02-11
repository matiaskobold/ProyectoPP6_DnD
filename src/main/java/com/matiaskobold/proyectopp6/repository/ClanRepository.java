package com.matiaskobold.proyectopp6.repository;

import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface ClanRepository extends JpaRepository<Clan, Long> {
}
