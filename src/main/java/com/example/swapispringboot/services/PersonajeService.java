package com.example.swapispringboot.services;

import com.example.swapispringboot.entities.Personaje;
import com.example.swapispringboot.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {
    private final PersonajeRepository personajeRepository;


    @Autowired
    public PersonajeService(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    public void save(Personaje personaje){
        personaje.corregirId();
        personajeRepository.save(personaje);
    }

    public Optional<Personaje> findPersonajeById(Integer id) {
        return personajeRepository.findById(id);
    }

    public List<Personaje> findAll(){
        return personajeRepository.findAll();
    }

    public Optional<Personaje> findPersonajeByNombre(String nombre){
        return personajeRepository.findPersonajeByName(nombre);
    }

}
