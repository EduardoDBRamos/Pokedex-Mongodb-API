package com.pokedex.service;

import com.pokedex.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PokedexService {

    public List<Pokemon> getAllPokemons() {
        return Arrays.asList(
                new Pokemon("Bulbasaur","Grass"),
                new Pokemon("Squirtle","Water")
        );
    }
}
