package com.meloeclaranhan.pokemons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meloeclaranhan.pokemons.orm.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
