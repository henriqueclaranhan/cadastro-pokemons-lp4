package com.meloeclaranhan.pokemons.orm;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "O nome do Pokémon deve ter no mínimo 3 carateres!")
	private String name;
	
	@NotNull
	private String type;

	@NotNull
	private String weakness;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	
	@NotNull
	@Size(min = 3, message = "O nome do dono do pokémon deve ter no mínimo 3 carateres!")
	private String pokemonOwnerName;

	private String imageSrc;
	
	public Pokemon() {}
	
	public Pokemon(String name, String type, String weakness, Date birthday, String pokemonOwnerName) {
		super();
		this.name = name;
		this.type = type;
		this.weakness = weakness;
		this.birthday = birthday;
		this.pokemonOwnerName = pokemonOwnerName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPokemonOwnerName() {
		return pokemonOwnerName;
	}

	public void setPokemonOwnerName(String pokemonOwnerName) {
		this.pokemonOwnerName = pokemonOwnerName;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
}
