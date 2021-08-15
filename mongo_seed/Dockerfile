FROM mongo

COPY kanto.json /kanto.json

CMD mongoimport --host mongo --db pokedex --collection pokemons --type json --file /kanto.json --jsonArray