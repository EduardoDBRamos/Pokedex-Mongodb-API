package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.resource.PokedexResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokedexService {
    PokedexResource resource = new PokedexResource();

    public List<Pokemon> getAllPokemons() {
        return resource.getAll();
    }
}
