package com.greydev.barebonejpaapp.simple;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userId;

	private String name;

	// @OneToMany(mappedBy = "user")
	// private List<Vehicle> vehicles;

	@ManyToMany
	// @JoinTable(
	// 		name = "my_super_table",
	// 		joinColumns = @JoinColumn(name = "fruid_id"),
	// 		inverseJoinColumns = @JoinColumn(name = "user_id")
	// )
	private List<Fruit> favoriteFruits = new ArrayList<>();


	public void addFavoriteFruit(Fruit fruit) {
		this.favoriteFruits.add(fruit);
	}
}

