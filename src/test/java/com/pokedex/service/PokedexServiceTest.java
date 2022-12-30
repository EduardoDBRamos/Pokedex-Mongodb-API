package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokedexRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    private final Pageable paging = PageRequest.of(0, 2, Sort.by("id"));

    @DisplayName("Confirm method existence")
    @Test
    void callMethodGetAllPokemons() throws NoSuchMethodException {
        Assert.notNull(service.getClass().getMethod("getAllPokemons", Pageable.class),
                "GetAllPokemons method does not exists");
    }

    @DisplayName("Should get all pokemons successfully")
    @Test
    void getAllPokemons(){
        List<Pokemon> expectedList = Collections.singletonList(
                new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass")));
        PageImpl<Pokemon> pageable = new PageImpl<>(expectedList);

        when(repository.findAll(any(Pageable.class))).thenReturn(pageable);

        ResponseEntity<Page<Pokemon>> returnService = service.getAllPokemons(paging);

        assertEquals(expectedList, Objects.requireNonNull(returnService.getBody()).toList());
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
        when(repository.findById(1L)).thenReturn(java.util.Optional.ofNullable(pokemonsList.get(0)));
        when(repository.findById(2L)).thenReturn(java.util.Optional.ofNullable(pokemonsList.get(1)));
        when(repository.findById(3L)).thenReturn(java.util.Optional.ofNullable(pokemonsList.get(2)));

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
        when(repository.findByNameRegex("Bulbasaur", paging)).thenReturn(new PageImpl<>(bulbasaur));
        when(repository.findByNameRegex("Ivysaur", paging)).thenReturn(new PageImpl<>(ivysaur));

        //ASSERT
        ResponseEntity<Page<Pokemon>> returnService = service.getPokemonName("Bulbasaur", paging);
        assertEquals(bulbasaur, Objects.requireNonNull(returnService.getBody()).toList());

        returnService = service.getPokemonName("Ivysaur", paging);
        assertEquals(ivysaur, Objects.requireNonNull(returnService.getBody()).toList());
    }

    @DisplayName("Can't find pokemons with name regex")
    @Test
    void cantFindPokemonsWithRegex(){
        //WHEN
        when(repository.findByNameRegex(any(), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        //ASSERT
        ResponseEntity<Page<Pokemon>> response = service.getPokemonName("NotExist", paging);
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
        when(repository.findByTypeRegex("Grass", paging)).thenReturn(new PageImpl<>(grassType));

        //ASSERT
        ResponseEntity<Page<Pokemon>> returnService = service.getPokemonsByType("Grass", paging);
        assertEquals(grassType, Objects.requireNonNull(returnService.getBody()).toList());
        assertEquals(HttpStatus.OK, returnService.getStatusCode());
    }

    @DisplayName("Can't find pokemons for unknown type")
    @Test
    void unknownPokemonType(){
        //WHEN
        when(repository.findByTypeRegex(any(), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        //ASSERT
        ResponseEntity<Page<Pokemon>> response = service.getPokemonsByType("UnknowType", paging);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
