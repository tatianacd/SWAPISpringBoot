package com.example.swapispringboot.controllers;

import com.example.swapispringboot.entities.Personaje;
import com.example.swapispringboot.services.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonajeController {

    private final PersonajeService personajeService;

    @Autowired
    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping(value = "/personaje/{id}", produces = "application/json")
    public String getPersonajeComoString(@PathVariable String id) {
        //objeto que permite hablar con una api
        RestTemplate apiStarWars = new RestTemplate();
        String personaje = null;
        try {
            Integer elId = Integer.parseInt(id);
            personaje = apiStarWars.getForObject("https://swapi.dev/api/people/" + id + "/", String.class);
        } catch (NumberFormatException | HttpClientErrorException e) {
            return "personaje no encontrado";
        }

        return (personaje == null) ? "Personaje o encontrado" : personaje;
    }

    @GetMapping(value = "/personajeapi/{id}")
    public Personaje getPersonaje(@PathVariable Integer id) {
        RestTemplate apiStarWars = new RestTemplate();
        //get for entity devuelve un responseEntity.
        ResponseEntity<Personaje> personaje = apiStarWars.getForEntity("https://swapi.dev/api/people/" + id + "/", Personaje.class);
        return personaje.getBody();

    }

    @GetMapping(value="/personaje/{id}/guardar", produces = "application/json")
    public Personaje leerAPIyGuardar(@PathVariable Integer id){
        RestTemplate apiStarWars = new RestTemplate();
        Personaje personaje = apiStarWars
                .getForEntity("https://swapi.dev/api/people/"+id+"/", Personaje.class)
                .getBody();
        personajeService.save(personaje);
        return personaje;
    }

    @GetMapping("/personajes")
    public List<Personaje> findAll(){
        return personajeService.findAll();
    }

    @GetMapping("/nombre/{nombre}")
    public Optional<Personaje> findByNombre(@PathVariable String nombre){
        Optional personajeBD = personajeService.findPersonajeByNombre(nombre);
        if(personajeBD!=null){
            return personajeBD;
        }else{
            RestTemplate apiStarWars = new RestTemplate();
            Personaje personaje = apiStarWars
                    .getForEntity("https://swapi.dev/api/people/?search=+nombre+", Personaje.class)
                    .getBody();
            personajeService.save(personaje);
            return personajeBD;
        }



    }
}

