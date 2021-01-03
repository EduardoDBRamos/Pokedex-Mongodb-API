FROM mongo
COPY pokedex.json /pokedex.json
CMD mongoimport --host mongodb --db pokedexDb --collection pokemons --type json --file /pokedex.json --jsonArray