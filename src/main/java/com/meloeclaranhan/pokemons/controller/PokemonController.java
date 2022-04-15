package com.meloeclaranhan.pokemons.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meloeclaranhan.pokemons.orm.Pokemon;
import com.meloeclaranhan.pokemons.repository.PokemonRepository;


@Controller
@RequestMapping("/pokemon")
public class PokemonController {
	@Autowired
	private PokemonRepository pokemonRepository;

	@GetMapping("/add")
	public String addPokemon(Model model) {
		Pokemon pokemon = new Pokemon();

		model.addAttribute("pokemon", pokemon);

		return "public/add-pokemon";
	}

	@PostMapping("/save")
	public String savePokemon(@Valid Pokemon pokemon, BindingResult result) {
		if (result.hasErrors()) {
			return "public/add-pokemon";
		}

		pokemonRepository.save(pokemon);

		return "redirect:/pokemon/admin/list";
	}

	@RequestMapping("/admin/list")
	public String listPokemons(Model model) {
		List<Pokemon> pokemons = pokemonRepository.findAll();

		model.addAttribute("pokemons", pokemons);

		return "/auth/admin/list-pokemons";
	}

	@GetMapping("/edit/{id}")
	public String editPokemon(@PathVariable("id") long id, Model model) {
		Optional<Pokemon> currentPokemon = pokemonRepository.findById(id);

		if (!currentPokemon.isPresent()) {
			throw new IllegalArgumentException("Id inválido: " + id);
		}

		Pokemon pokemon = currentPokemon.get();
		model.addAttribute("pokemon", pokemon);

		return "/auth/user/edit-pokemon";
	}

	@PostMapping("/edit/{id}")
	public String editPokemon(@PathVariable("id") long id, @Valid Pokemon pokemon, BindingResult result) {
		if (result.hasErrors()) {
			pokemon.setId(id);
			return "/auth/user/edit-pokemon";
		}

		pokemonRepository.save(pokemon);

		return "redirect:/pokemon/admin/list";
	}

	@RequestMapping("/admin/delete/{id}")
	public String deletePokemon(@PathVariable("id") long id, Model model) {
		Pokemon pokemon = pokemonRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));

		pokemonRepository.delete(pokemon);		

		return "redirect:/pokemon/admin/list";
	}

}
