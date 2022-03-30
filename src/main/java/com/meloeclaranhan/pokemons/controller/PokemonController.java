package com.meloeclaranhan.pokemons.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meloeclaranhan.pokemons.orm.Pokemon;
import com.meloeclaranhan.pokemons.repository.PokemonRepository;


@Controller
public class PokemonController {
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@RequestMapping("/")
	public String homeRoute(Model model) {
		return "index";
	}
	
	@RequestMapping("/list")
	public String listPokemonsRoute(Model model) {
		List<Pokemon> pokes = pokemonRepository.findAll();
		model.addAttribute("pokemons", pokes);
		return "list-pokemons";
	}
	
	@GetMapping("/add")
	public String addPokemonRoute(Model model) {
		Pokemon poke = new Pokemon();
		model.addAttribute("pokemon", poke);
		return "form-pokemon";
	}
	
	@PostMapping("/save")
	public String savePokemonRoute(@Valid Pokemon poke, BindingResult result) {
		if(result.hasErrors()) {
			return "form-pokemon";
		}
		pokemonRepository.save(poke);
		return "redirect:/list";
	}
}
