package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(PokedexUrls.URL_BASE)
public class PokedexController {
    @Autowired
    PokedexService service;

    @GetMapping(PokedexUrls.GET_ALL)
    public ResponseEntity<List<Pokemon>> getAll(){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAllPokemons());
    }

    @GetMapping(PokedexUrls.GET_POKEMON+"/{id}")
    public ResponseEntity<Pokemon> getPokemonId(@PathVariable(value = "id") int id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getPokemon(id));
    }

    @GetMapping(PokedexUrls.GET_POKEMON)
    public ResponseEntity<String> getPokemonId(){
        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body("forward:"+ PokedexUrls.GET_ALL);
    }
}
