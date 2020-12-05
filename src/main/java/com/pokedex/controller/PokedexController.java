package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Pokemon>> index(){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAllPokemons());
    }
}
