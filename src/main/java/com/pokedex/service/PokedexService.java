package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokedexService {
    @Autowired
    private PokedexRepository repository;

    public  ResponseEntity<List<Pokemon>> getAllPokemons() {
        return ResponseEntity.ok().body(repository.findAll());
    }
    public ResponseEntity<Pokemon> getPokemonId(long l) {
        Optional<Pokemon> result = repository.findById(String.valueOf(l));
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Pokemon>> getPokemonName(String name) {
        List<Pokemon> pokemons = repository.findByNameRegex(name);
        if(pokemons.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pokemons);
    }

    public ResponseEntity<List<Pokemon>> getPokemonsByType(String type) {
        List<Pokemon> pokemons = repository.findByTypeRegex(type);
        if(pokemons.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pokemons);
    }
}
