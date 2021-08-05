package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(PokedexUrls.GET_POKEMON)
    public Pokemon getPokemonName(
            @RequestParam(name = "number", defaultValue = "0") Long number,
            @RequestParam(name = "name", defaultValue = "Bulbasaur") String name){
        return service.getPokemon(number, name);
    }
}
