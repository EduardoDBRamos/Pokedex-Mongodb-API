package com.pokedex.resource;

import com.pokedex.model.Pokemon;

import java.util.Arrays;
import java.util.List;

public class PokedexResource {

    public List<Pokemon> getAll() {
        return Arrays.asList(
                new Pokemon("Bulbasaur","Grass"),
                new Pokemon("Squirtle","Water")
        );
    }
}
