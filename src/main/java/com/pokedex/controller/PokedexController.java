package com.pokedex.controller;

import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class PokedexController {

    PokedexService service = new PokedexService();
    @RequestMapping("/")
    public Pokemon index(){
        return service.helloWorld();
    }
}
