package com.pokedex.controller;

import com.pokedex.service.PokedexService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PokedexController.class)
class PokedexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokedexService service;

    private final Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "_id");

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
        when(service.getAllPokemons(any())).thenReturn(null);

        MvcResult result = mockMvc.perform(
                get("/pokedex/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();

        verify(service, times(1)).getAllPokemons(any());
    }

    @DisplayName("Get a pokemon by ID passing int return success")
    @Test
    void callGetPokemonByID() throws Exception {
        //WHEN
        when(service.
                getPokemonId(eq(1L))).thenReturn(null);

        //ASSERT
        MvcResult result = mockMvc.perform(
                get("/pokedex/number/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();

        verify(service, times(1)).getPokemonId(1L);
    }

    @DisplayName("Pass a correct pokemon name as parameter on get method")
    @Test
    void callGetPokemonByNameACorrectName() throws Exception {
        //WHEN
        when(service.getPokemonName("Bulbasaur", paging)).thenReturn(null);

        //ASSERT
        MvcResult result = mockMvc.perform(
                get("/pokedex/name/Bulbasaur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        verify(service, times(1)).getPokemonName("Bulbasaur", paging);
    }

    @DisplayName("Should return pokemons with water type")
    @Test
    void callGetPokemonByType() throws Exception {
        //WHEN
        when(service.getPokemonsByType("water", paging)).thenReturn(null);

        //ASSERT
        MvcResult result = mockMvc.perform(
                get("/pokedex/type/water")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();

        verify(service, times(1)).getPokemonsByType("water", paging);    }
}
