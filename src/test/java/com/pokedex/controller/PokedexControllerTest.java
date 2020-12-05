package com.pokedex.controller;

import com.pokedex.constants.PokedexUrls;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PokedexController.class)
class PokedexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PokedexService service;

    @DisplayName("Call getall URL with success")
    @Test
    void callGetAllPokemonsWithSuccess() throws Exception {
        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass");
        List<Pokemon> mockList = Collections.singletonList(bulbasaur);
        when(service.getAllPokemons()).thenReturn(mockList);

        MvcResult result = mockMvc.perform(
                get(PokedexUrls.URL_BASE + PokedexUrls.GET_ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())).andReturn();

        assertEquals("[{\"name\":\"Bulbasaur\",\"type\":\"Grass\"}]",
                result.getResponse().getContentAsString());
    }

    @DisplayName("Call getall URL without base URL")
    @Test
    void callGetAllUrlWithoutBaseUrl() throws Exception {
        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass");
        List<Pokemon> mockList = Collections.singletonList(bulbasaur);
        when(service.getAllPokemons()).thenReturn(mockList);

        mockMvc.perform(
                get(PokedexUrls.GET_ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
