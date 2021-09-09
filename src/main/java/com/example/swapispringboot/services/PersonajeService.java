package com.example.swapispringboot.services;

import com.example.swapispringboot.entities.Personaje;
import com.example.swapispringboot.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
