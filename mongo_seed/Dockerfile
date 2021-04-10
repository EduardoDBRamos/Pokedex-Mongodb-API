FROM mongo

COPY pokedex.json /pokedex.json

CMD mongoimport --host mongo --db pokedex --collection pokemons --type json --file /pokedex.json --jsonArray