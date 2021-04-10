package com.pokedex.repository;

import com.pokedex.model.Pokemon;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "pokedex")
@Document(collection="pokemons")
public interface PokedexRepository extends MongoRepository<Pokemon, String> {
}
