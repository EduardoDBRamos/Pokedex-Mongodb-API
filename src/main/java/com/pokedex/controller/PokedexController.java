package com.pokedex.controller;

import com.pokedex.model.PokemonDTO;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

@Api
@RestController
@RequestMapping("/pokedex")
public class PokedexController {
    @Autowired
    PokedexService service;
    @GetMapping
    @Cacheable("allPokemons")
    public ResponseEntity<Page<PokemonDTO>> getAll(
            @PageableDefault(sort = "_id",
                direction = Sort.Direction.ASC) Pageable pageable){
        return service.getAllPokemons(pageable);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<PokemonDTO> getPokemonId(@PathVariable(name = "number") Long number){
        return service.getPokemonId(number);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<PokemonDTO>> getPokemonName(
            @PageableDefault(sort = "_id",
                    direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(name = "name") String name){
        return service.getPokemonName(name, pageable);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<PokemonDTO>> getPokemonType(
            @PageableDefault(sort = "_id",
                    direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(name = "type") String type){
        return service.getPokemonsByType(type, pageable);
    }
}
