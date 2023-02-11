package com.matiaskobold.proyectopp6.repository;

import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SongRepository extends JpaRepository<Song, Long>{

    List<Song> findByClanId(Long clanId);
    void deleteByClanId(Long clanId);
}
