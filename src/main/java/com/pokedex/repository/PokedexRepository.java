package com.pokedex.repository;

import com.pokedex.model.Pokemon;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "pokedex")
@Document(collection="pokemons")
public interface PokedexRepository extends MongoRepository<Pokemon, String> {
    @Query("{name:?0}")
    Pokemon findByName(String name);
}
