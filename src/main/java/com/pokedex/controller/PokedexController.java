package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(PokedexUrls.URL_BASE)
public class PokedexController {
    @Autowired
    PokedexService service;

    @GetMapping(PokedexUrls.GET_ALL)
    public List<Pokemon> getAll(){
        return service.getAllPokemons();
    }

    @GetMapping(PokedexUrls.GET_POKEMON_NUMBER+"/{number}")
    public Pokemon getPokemonId(
            @PathVariable(name = "number") Long number){
        return service.getPokemonId(number);
    }

    @GetMapping(PokedexUrls.GET_POKEMON_NAME+"/{name}")
    public List<Pokemon> getPokemonName(
            @PathVariable(name = "name") String name){
        return service.getPokemonName(name);
    }

    @GetMapping(PokedexUrls.GET_POKEMON_TYPE+"/{type}")
    public List<Pokemon> getPokemonType(
            @PathVariable(name = "type") String type){
        return service.getPokemonsByType(type);
    }
}
