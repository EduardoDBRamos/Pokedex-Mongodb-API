package com.pokedex.model;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Getter
public class PokemonDTO {
    private String name;
    private List<String> type;

    private PokemonDTO(){}
    public PokemonDTO(Pokemon pokemon) {
        this.name = pokemon.getName();
        this.type = pokemon.getType();
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("name", this.name)
                .append("type", this.type)
                .build();
    }
}
