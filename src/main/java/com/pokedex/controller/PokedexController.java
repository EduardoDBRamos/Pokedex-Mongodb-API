package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
import com.pokedex.model.Pokemon;
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

import java.util.List;

@Api
@RestController
@RequestMapping(PokedexUrls.URL_BASE)
public class PokedexController {
    @Autowired
    PokedexService service;
    @GetMapping
    @Cacheable("allPokemons")
    public ResponseEntity<Page<Pokemon>> getAll(
            @PageableDefault(sort = "_id",
                direction = Sort.Direction.ASC) Pageable pageable){
        return service.getAllPokemons(pageable);
    }

    @GetMapping(PokedexUrls.GET_POKEMON_NUMBER+"/{number}")
    public ResponseEntity<Pokemon> getPokemonId(@PathVariable(name = "number") Long number){
        return service.getPokemonId(number);
    }

    @GetMapping(PokedexUrls.GET_POKEMON_NAME+"/{name}")
    public ResponseEntity<Page<Pokemon>> getPokemonName(
            @PageableDefault(sort = "_id",
                    direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(name = "name") String name){
        return service.getPokemonName(name, pageable);
    }

    @GetMapping(PokedexUrls.GET_POKEMON_TYPE+"/{type}")
    public ResponseEntity<Page<Pokemon>> getPokemonType(
            @PageableDefault(sort = "_id",
                    direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(name = "type") String type){
        return service.getPokemonsByType(type, pageable);
    }
}
