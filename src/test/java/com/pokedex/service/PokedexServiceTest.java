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

    @DisplayName("Get all pokemons successfully")
    @Test
    void getAllPokemons(){
        List<Pokemon> expectedList = Collections.singletonList(
                new Pokemon(1, "Bulbasaur", Collections.singletonList("Grass")));
        when(repository.findAll()).thenReturn(expectedList);

        List<Pokemon> returnService = service.getAllPokemons();

        Assertions.assertEquals(expectedList, returnService);
    }

    @DisplayName("Get correct pokemon by Id")
    @Test
    void getPokemonById(){
        //GIVEN
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", Collections.singletonList("Grass"));
        Pokemon ivysaur = new Pokemon(2, "Ivysaur", Collections.singletonList("Grass"));
        Pokemon venusaur = new Pokemon(3, "Venusaur", Collections.singletonList("Grass"));

        List<Pokemon> pokemonsList = Arrays.asList(bulbasaur, ivysaur, venusaur);
        //WHEN
        when(repository.findById(1)).thenReturn(pokemonsList.get(0));
        when(repository.findById(2)).thenReturn(pokemonsList.get(1));
        when(repository.findById(3)).thenReturn(pokemonsList.get(2));

        //ASSERT
        Pokemon returnService = service.getPokemon(1);
        Assertions.assertEquals(bulbasaur, returnService);

        returnService = service.getPokemon(2);
        Assertions.assertEquals(ivysaur, returnService);

        returnService = service.getPokemon(3);
        Assertions.assertEquals(venusaur, returnService);
    }
}
