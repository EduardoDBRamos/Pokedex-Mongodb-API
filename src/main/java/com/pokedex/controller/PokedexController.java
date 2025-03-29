package com.pokedex.controller;

import com.pokedex.model.PokemonDTO;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import java.util.Optional;

@Api
@RestController
@RequestMapping("/pokedex")
public class PokedexController {
    @Autowired
    private PokedexService service;

    @GetMapping
    @Cacheable("allPokemons")
    public ResponseEntity<Page<PokemonDTO>> getAll(Integer page, Integer size){
        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(size).orElse(10),
                Sort.Direction.ASC, "_id");

        return service.getAllPokemons(pageable);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<PokemonDTO> getPokemonId(@PathVariable(name = "number") Long number){
        return service.getPokemonId(number);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<PokemonDTO>> getPokemonName(
            @PathVariable(name = "name") String name,
            Integer page,
            Integer size){
        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(size).orElse(10),
                Sort.Direction.ASC, "_id");
        return service.getPokemonName(name, pageable);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<PokemonDTO>> getPokemonType(
            @PathVariable(name = "type") String type,
            Integer page,
            Integer size){

        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(size).orElse(10),
                Sort.Direction.ASC, "_id");
        return service.getPokemonsByType(type, pageable);
    }
}
