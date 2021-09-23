package com.example.swapispringboot.repositories;

import com.example.swapispringboot.entities.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    Optional<Personaje> findPersonajeByName(String name);

}
