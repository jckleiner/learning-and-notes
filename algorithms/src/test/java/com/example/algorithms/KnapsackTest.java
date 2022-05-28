package com.example.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class KnapsackTest {

	@Test
	void knapsack_3items() {
		Knapsack.Tuple<Integer, List<Knapsack.Item>> result = Knapsack.knapsack(build3Items(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(35);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("guitar", "laptop"));
	}


	@Test
	void knapsack_4items() {
		Knapsack.Tuple<Integer, List<Knapsack.Item>> result = Knapsack.knapsack(build4Items(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(40);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("iphone", "laptop"));
	}


	@Test
	void knapsack_5items() {
		Knapsack.Tuple<Integer, List<Knapsack.Item>> result = Knapsack.knapsack(build5Items(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(45);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("iphone", "guitar", "mp3"));
	}


	@Test
	void knapsack_6CampingItems() {
		Knapsack.Tuple<Integer, List<Knapsack.Item>> result = Knapsack.knapsack(build6CampingItems(), 6);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(25);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("camera", "water", "food"));
	}


	@Test
	void knapsack_6LondonTravelDestinations() {
		Knapsack.Tuple<Integer, List<Knapsack.Item>> result = Knapsack.knapsack(build6LondonTravelDestinations(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(24);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("St. Paul's Cathedral", "National Gallery", "Westminister Abbey"));
	}


	private List<Knapsack.Item> build3Items() {
		Knapsack.Item guitar = Knapsack.Item.builder()
				.name("guitar")
				.weight(1)
				.value(15)
				.build();

		Knapsack.Item stereo = Knapsack.Item.builder()
				.name("stereo")
				.weight(4)
				.value(30)
				.build();

		Knapsack.Item laptop = Knapsack.Item.builder()
				.name("laptop")
				.weight(3)
				.value(20)
				.build();

		return new ArrayList<>(Arrays.asList(guitar, stereo, laptop));
	}


	private List<Knapsack.Item> build4Items() {
		Knapsack.Item iphone = Knapsack.Item.builder()
				.name("iphone")
				.weight(1)
				.value(20)
				.build();

		List<Knapsack.Item> items = build3Items();
		items.add(iphone);

		return items;
	}


	private List<Knapsack.Item> build5Items() {
		Knapsack.Item mp3 = Knapsack.Item.builder()
				.name("mp3")
				.weight(1)
				.value(10)
				.build();

		List<Knapsack.Item> items = build4Items();
		items.add(mp3);

		return items;
	}


	private List<Knapsack.Item> build6CampingItems() {
		Knapsack.Item water = Knapsack.Item.builder()
				.name("water")
				.weight(3)
				.value(10)
				.build();

		Knapsack.Item book = Knapsack.Item.builder()
				.name("book")
				.weight(1)
				.value(3)
				.build();

		Knapsack.Item food = Knapsack.Item.builder()
				.name("food")
				.weight(2)
				.value(9)
				.build();

		Knapsack.Item jacket = Knapsack.Item.builder()
				.name("jacket")
				.weight(2)
				.value(5)
				.build();

		Knapsack.Item camera = Knapsack.Item.builder()
				.name("camera")
				.weight(1)
				.value(6)
				.build();

		return new ArrayList<>(Arrays.asList(water, book, food, jacket, camera));
	}


	private List<Knapsack.Item> build6LondonTravelDestinations() {
		Knapsack.Item brm = Knapsack.Item.builder()
				.name("British Museum")
				.weight(4)
				.value(9)
				.build();

		Knapsack.Item st = Knapsack.Item.builder()
				.name("St. Paul's Cathedral")
				.weight(1)
				.value(8)
				.build();

		Knapsack.Item nat = Knapsack.Item.builder()
				.name("National Gallery")
				.weight(2)
				.value(9)
				.build();

		Knapsack.Item globe = Knapsack.Item.builder()
				.name("Globe Theater")
				.weight(1)
				.value(6)
				.build();

		Knapsack.Item west = Knapsack.Item.builder()
				.name("Westminister Abbey")
				.weight(1)
				.value(7)
				.build();

		return new ArrayList<>(Arrays.asList(brm, st, nat, globe, west));
	}

}