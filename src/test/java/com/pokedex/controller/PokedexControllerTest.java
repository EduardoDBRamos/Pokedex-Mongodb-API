package com.pokedex.controller;

import com.pokedex.model.Pokemon;
import com.pokedex.service.PokedexService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PokedexController.class)
class PokedexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PokedexService service;

    @DisplayName("Call getall URL without base URL")
    @Test
    void callGetAllUrlWithoutBaseUrl() throws Exception {
        mockMvc.perform(
                get("/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @DisplayName("Call getall URL with success")
    @Test
    void callGetAllPokemonsWithSuccess() throws Exception {
        Pokemon bulbasaur =
                new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass"));
        List<Pokemon> mockList = Collections.singletonList(bulbasaur);
        when(service.getAllPokemons()).thenReturn(mockList);

        MvcResult result = mockMvc.perform(
                get("/pokedex/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();

        assertEquals("[{\"id\":1,\"name\":\"Bulbasaur\",\"type\":[\"Grass\"]}]",
                result.getResponse().getContentAsString());
    }

    @DisplayName("Get a pokemon by ID passing int return success")
    @Test
    void callGetPokemonByID() throws Exception {
        //GIVEN
        Pokemon bulbasaur =
                new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass"));
        //WHEN
        when(service.
                getPokemonId(eq(1L))).thenReturn(bulbasaur);

        //ASSERT
        MvcResult result = mockMvc.perform(
                get("/pokedex/number/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();

        assertEquals("{\"id\":1,\"name\":\"Bulbasaur\",\"type\":[\"Grass\"]}",
                result.getResponse().getContentAsString());

    }

    @DisplayName("Pass a correct pokemon name as parameter on get method")
    @Test
    void callGetPokemonByNameACorrectName() throws Exception {
        //GIVEN
        List<Pokemon> bulbasaur = Collections.singletonList(
                new Pokemon(1L, "Bulbasaur", Collections.singletonList("Grass")));
        //WHEN
        when(service.getPokemonName("Bulbasaur")).thenReturn(bulbasaur);

        //ASSERT
        MvcResult result = mockMvc.perform(
                get("/pokedex/name/Bulbasaur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();
        assertEquals("[{\"id\":1,\"name\":\"Bulbasaur\",\"type\":[\"Grass\"]}]", result.getResponse().getContentAsString());
    }

}
