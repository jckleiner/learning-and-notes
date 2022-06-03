package com.example.algorithms;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class GraphTest {

	@Test
	void testBreadthFirstPrint() {
		assertThat(Graph.breadthFirstPrint(buildGraph()))
				.isEqualTo(Arrays.asList("A", "B", "C", "D", "E", "F"));
	}


	@Test
	void testDepthFirstPrint() {
		assertThat(Graph.depthFirstPrint(buildGraph()))
				.isEqualTo(Arrays.asList("A", "C", "E", "B", "D", "F"));
	}


	Map<String, List<String>> buildGraph() {
		Map<String, List<String>> map = new HashMap<>();
		map.put("A", Arrays.asList("B", "C"));
		map.put("B", Arrays.asList("D"));
		map.put("C", Arrays.asList("E"));
		map.put("D", Arrays.asList("F"));
		map.put("E", Collections.emptyList());
		map.put("F", Collections.emptyList());
		return map;
	}

}