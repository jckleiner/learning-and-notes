package com.example.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.example.algorithms.util.Tuple;

import static org.assertj.core.api.Assertions.assertThat;


class KnapsackTest {

	@Test
	void testKnapsack_01_3items() {
		Tuple<Integer, List<Knapsack_01.Item>> result = Knapsack_01.knapsack_01(build3Items(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack_01.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(35);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("guitar", "laptop"));
	}


	@Test
	void testKnapsack_01_4items() {
		Tuple<Integer, List<Knapsack_01.Item>> result = Knapsack_01.knapsack_01(build4Items(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack_01.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(40);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("iphone", "laptop"));
	}


	@Test
	void testKnapsack_01_5items() {
		Tuple<Integer, List<Knapsack_01.Item>> result = Knapsack_01.knapsack_01(build5Items(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack_01.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(45);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("iphone", "guitar", "mp3"));
	}


	@Test
	void testKnapsack_01_6CampingItems() {
		Tuple<Integer, List<Knapsack_01.Item>> result = Knapsack_01.knapsack_01(build6CampingItems(), 6);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack_01.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(25);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("camera", "water", "food"));
	}


	@Test
	void testKnapsack_01_6LondonTravelDestinations() {
		Tuple<Integer, List<Knapsack_01.Item>> result = Knapsack_01.knapsack_01(build6LondonTravelDestinations(), 4);
		List<String> namesOfPickedItems = result.getSecond().stream()
				.map(Knapsack_01.Item::getName)
				.collect(Collectors.toList());

		assertThat(result.getFirst()).isEqualTo(24);
		assertThat(namesOfPickedItems).containsAll(Arrays.asList("St. Paul's Cathedral", "National Gallery", "Westminister Abbey"));
	}


	private List<Knapsack_01.Item> build3Items() {
		Knapsack_01.Item guitar = Knapsack_01.Item.builder()
				.name("guitar")
				.weight(1)
				.value(15)
				.build();

		Knapsack_01.Item stereo = Knapsack_01.Item.builder()
				.name("stereo")
				.weight(4)
				.value(30)
				.build();

		Knapsack_01.Item laptop = Knapsack_01.Item.builder()
				.name("laptop")
				.weight(3)
				.value(20)
				.build();

		return new ArrayList<>(Arrays.asList(guitar, stereo, laptop));
	}


	private List<Knapsack_01.Item> build4Items() {
		Knapsack_01.Item iphone = Knapsack_01.Item.builder()
				.name("iphone")
				.weight(1)
				.value(20)
				.build();

		List<Knapsack_01.Item> items = build3Items();
		items.add(iphone);

		return items;
	}


	private List<Knapsack_01.Item> build5Items() {
		Knapsack_01.Item mp3 = Knapsack_01.Item.builder()
				.name("mp3")
				.weight(1)
				.value(10)
				.build();

		List<Knapsack_01.Item> items = build4Items();
		items.add(mp3);

		return items;
	}


	private List<Knapsack_01.Item> build6CampingItems() {
		Knapsack_01.Item water = Knapsack_01.Item.builder()
				.name("water")
				.weight(3)
				.value(10)
				.build();

		Knapsack_01.Item book = Knapsack_01.Item.builder()
				.name("book")
				.weight(1)
				.value(3)
				.build();

		Knapsack_01.Item food = Knapsack_01.Item.builder()
				.name("food")
				.weight(2)
				.value(9)
				.build();

		Knapsack_01.Item jacket = Knapsack_01.Item.builder()
				.name("jacket")
				.weight(2)
				.value(5)
				.build();

		Knapsack_01.Item camera = Knapsack_01.Item.builder()
				.name("camera")
				.weight(1)
				.value(6)
				.build();

		return new ArrayList<>(Arrays.asList(water, book, food, jacket, camera));
	}


	private List<Knapsack_01.Item> build6LondonTravelDestinations() {
		Knapsack_01.Item brm = Knapsack_01.Item.builder()
				.name("British Museum")
				.weight(4)
				.value(9)
				.build();

		Knapsack_01.Item st = Knapsack_01.Item.builder()
				.name("St. Paul's Cathedral")
				.weight(1)
				.value(8)
				.build();

		Knapsack_01.Item nat = Knapsack_01.Item.builder()
				.name("National Gallery")
				.weight(2)
				.value(9)
				.build();

		Knapsack_01.Item globe = Knapsack_01.Item.builder()
				.name("Globe Theater")
				.weight(1)
				.value(6)
				.build();

		Knapsack_01.Item west = Knapsack_01.Item.builder()
				.name("Westminister Abbey")
				.weight(1)
				.value(7)
				.build();

		return new ArrayList<>(Arrays.asList(brm, st, nat, globe, west));
	}

}