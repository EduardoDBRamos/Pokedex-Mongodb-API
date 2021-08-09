package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokedexRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokedexServiceTest {
    @InjectMocks
    private PokedexService service;

    @Mock
    PokedexRepository repository;

    @DisplayName("Confirm method existence")
    @Test
    void callMethodGetAllPokemons() throws NoSuchMethodException {
        Assert.notNull(service.getClass().getMethod("getAllPokemons"),
                "GetAllPokemons method does not exists");
    }

    @DisplayName("Should get all pokemons successfully")
    @Test
    void getAllPokemons(){
        List<Pokemon> expectedList = Collections.singletonList(
                new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass")));
        when(repository.findAll()).thenReturn(expectedList);

        List<Pokemon> returnService = service.getAllPokemons();

        Assertions.assertEquals(expectedList, returnService);
    }

    @DisplayName("Should get correct pokemon by Id")
    @Test
    void getPokemonById(){
        //GIVEN
        Pokemon bulbasaur = new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass"));
        Pokemon ivysaur = new Pokemon(2L, "Ivysaur", Collections.singletonList("Grass"));
        Pokemon venusaur = new Pokemon(3L, "Venusaur", Collections.singletonList("Grass"));

        List<Pokemon> pokemonsList = Arrays.asList(bulbasaur, ivysaur, venusaur);
        //WHEN
        when(repository.findById("1")).thenReturn(java.util.Optional.ofNullable(pokemonsList.get(0)));
        when(repository.findById("2")).thenReturn(java.util.Optional.ofNullable(pokemonsList.get(1)));
        when(repository.findById("3")).thenReturn(java.util.Optional.ofNullable(pokemonsList.get(2)));

        //ASSERT
        Pokemon returnService = service.getPokemonId(1L);
        Assertions.assertEquals(bulbasaur, returnService);

        returnService = service.getPokemonId(2L);
        Assertions.assertEquals(ivysaur, returnService);

        returnService = service.getPokemonId(3L);
        Assertions.assertEquals(venusaur, returnService);
    }

    @DisplayName("Should get a pokemon by name")
    @Test
    void getPokemonByName(){
        //GIVEN
        List<Pokemon> bulbasaur = Collections.singletonList(new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass")));
        List<Pokemon> ivysaur = Collections.singletonList(new Pokemon(2L, "Ivysaur", Collections.singletonList("Grass")));

        //WHEN
        when(repository.findByName("Bulbasaur")).thenReturn(bulbasaur);
        when(repository.findByName("Ivysaur")).thenReturn(ivysaur);

        //ASSERT
        List<Pokemon> returnService = service.getPokemonName("Bulbasaur");
        Assertions.assertEquals(bulbasaur, returnService);

        returnService = service.getPokemonName("Ivysaur");
        Assertions.assertEquals(ivysaur, returnService);
    }
}
