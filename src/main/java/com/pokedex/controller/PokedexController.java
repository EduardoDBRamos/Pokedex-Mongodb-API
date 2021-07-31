package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(PokedexUrls.GET_POKEMON+"/{number}")
    public Pokemon getPokemonNumber(@PathVariable(value = "number") Long number){
        return service.getPokemon(number);
    }
}
