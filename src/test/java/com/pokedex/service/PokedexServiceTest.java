package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokedexRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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

        ResponseEntity<List<Pokemon>> returnService = service.getAllPokemons();

        assertEquals(expectedList, returnService.getBody());
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
        ResponseEntity<Pokemon> returnService = service.getPokemonId(1L);
        assertEquals(bulbasaur, returnService.getBody());

        returnService = service.getPokemonId(2L);
        assertEquals(ivysaur, returnService.getBody());

        returnService = service.getPokemonId(3L);
        assertEquals(venusaur, returnService.getBody());
    }

    @DisplayName("Should get a pokemon by name")
    @Test
    void getPokemonByName(){
        //GIVEN
        List<Pokemon> bulbasaur = Collections.singletonList(new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass")));
        List<Pokemon> ivysaur = Collections.singletonList(new Pokemon(2L, "Ivysaur", Collections.singletonList("Grass")));

        //WHEN
        when(repository.findByNameRegex("Bulbasaur")).thenReturn(bulbasaur);
        when(repository.findByNameRegex("Ivysaur")).thenReturn(ivysaur);

        //ASSERT
        ResponseEntity<List<Pokemon>> returnService = service.getPokemonName("Bulbasaur");
        assertEquals(bulbasaur, returnService.getBody());

        returnService = service.getPokemonName("Ivysaur");
        assertEquals(ivysaur, returnService.getBody());
    }

    @DisplayName("Can't find pokemons with name regex")
    @Test
    void cantFindPokemonsWithRegex(){
        //WHEN
        when(repository.findByNameRegex(any())).thenReturn(Collections.emptyList());

        //ASSERT
        ResponseEntity<List<Pokemon>> response = service.getPokemonName("NotExist");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @DisplayName("Should return pokemons with Grass type")
    @Test
    void getPokemonWithGrassType(){
        //GIVEN
        List<Pokemon> grassType = Arrays.asList(
                new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass")),
                new Pokemon(2L, "Ivysaur", Collections.singletonList("Grass")),
                new Pokemon(3L, "Venusaur", Collections.singletonList("Grass"))
        );
        //WHEN
        when(repository.findByTypeRegex("Grass")).thenReturn(grassType);

        //ASSERT
        ResponseEntity<List<Pokemon>> returnService = service.getPokemonsByType("Grass");
        assertEquals(grassType, returnService.getBody());
        assertEquals(HttpStatus.OK, returnService.getStatusCode());
    }

    @DisplayName("Can't find pokemons for unknown type")
    @Test
    void unknownPokemonType(){
        //WHEN
        when(repository.findByTypeRegex(any())).thenReturn(Collections.emptyList());

        //ASSERT
        ResponseEntity<List<Pokemon>> response = service.getPokemonsByType("UnknowType");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
