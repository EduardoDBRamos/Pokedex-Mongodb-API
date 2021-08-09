package com.pokedex.repository;

import com.pokedex.model.Pokemon;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "pokedex")
@Document(collection="pokemons")
public interface PokedexRepository extends MongoRepository<Pokemon, String> {
    @Query(value = "{name:{$regex:?0, $options: 'i'}}", fields = "{'id' : 1, 'name' : 1}")
    List<Pokemon> findByName(String name);
}
