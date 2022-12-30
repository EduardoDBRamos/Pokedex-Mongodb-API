package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokedexService {
    @Autowired
    private PokedexRepository repository;

    public  ResponseEntity<Page<Pokemon>> getAllPokemons(Pageable pageable) {
        return ResponseEntity.ok().body(repository.findAll(pageable));
    }
    public ResponseEntity<Pokemon> getPokemonId(long l) {
        Optional<Pokemon> result = repository.findById(l);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Page<Pokemon>> getPokemonName(String name, Pageable pageable) {
        Page<Pokemon> pokemons = repository.findByNameRegex(name, pageable);
        if(pokemons.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pokemons);
    }

    public ResponseEntity<Page<Pokemon>> getPokemonsByType(String type, Pageable pageable) {
        Page<Pokemon> pokemons = repository.findByTypeRegex(type, pageable);
        if(pokemons.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pokemons);
    }
}
