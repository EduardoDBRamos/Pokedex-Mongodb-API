package com.pokedex.service;

import com.pokedex.model.Pokemon;

public class PokedexService {
    public Pokemon helloWorld() {
        Pokemon pokemon = new Pokemon("Bulbasaur","Grass");
        return pokemon;
    }
}
