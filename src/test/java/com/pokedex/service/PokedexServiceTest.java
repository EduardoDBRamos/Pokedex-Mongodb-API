package com.pokedex.service;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PokedexServiceTest {
    PokedexService service = new PokedexService();

    @Test
    void callMethodGetAllPokemons() throws NoSuchMethodException {
        Assert.notNull(service.getClass().getMethod("getAllPokemons"),
                "GetAllPokemons method does not exists");
    }
}
