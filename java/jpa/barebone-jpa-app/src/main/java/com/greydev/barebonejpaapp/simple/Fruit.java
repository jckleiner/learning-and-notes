package com.greydev.barebonejpaapp.simple;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
public class Fruit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "favoriteFruits")
	private List<User> users = new ArrayList<>();


	public void addUser(User user) {
		this.users.add(user);
	}
}
