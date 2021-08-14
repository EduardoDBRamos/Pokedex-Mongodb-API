package com.pokedex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pokemons")
public class Pokemon {
    @Id
    private Long id;
    private String name;
    private List<String> type;

    @Override
    public String toString(){
        return String.format(
                "Pokemon[number=%s, name=%s, types=%s]",
                id, name, type.toString()
        );
    }
}
