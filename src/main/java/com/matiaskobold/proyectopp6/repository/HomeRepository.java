package com.matiaskobold.proyectopp6.repository;

import com.matiaskobold.proyectopp6.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
}
