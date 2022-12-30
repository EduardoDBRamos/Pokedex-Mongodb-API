package com.pokedex.repository;

import com.pokedex.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "pokedex")
@Document(collection="pokemons")
public interface PokedexRepository extends MongoRepository<Pokemon, Long> {

    @Query(value = "{name:{$regex:?0, $options: 'i'}}")
    Page<Pokemon> findByNameRegex(String name, Pageable pageable);
    Page<Pokemon> findByTypeRegex(String type, Pageable pageable);
}
