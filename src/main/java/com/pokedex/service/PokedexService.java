package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokedexService {
    @Autowired
    private PokedexRepository repository;

    public List<Pokemon> getAllPokemons() {
        return repository.findAll();
    }
}
