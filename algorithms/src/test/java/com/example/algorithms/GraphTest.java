package com.example.algorithms;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class GraphTest {

	@Test
	void testBreadthFirstPrint() {
		assertThat(Graph.breadthFirstPrint(buildAcyclicGraph(), "A"))
				.isEqualTo(Arrays.asList("A", "B", "C", "D", "E", "F"));

		assertThat(Graph.breadthFirstPrint(buildCyclicGraph(), "A"))
				.isEqualTo(Arrays.asList("A", "B", "C", "D", "E", "F"));
	}


	@Test
	void testDepthFirstPrint() {
		assertThat(Graph.depthFirstPrint(buildAcyclicGraph(), "A"))
				.isEqualTo(Arrays.asList("A", "C", "E", "B", "D", "F"));

		assertThat(Graph.depthFirstPrint(buildCyclicGraph(), "A"))
				.isEqualTo(Arrays.asList("A", "C", "E", "B", "D", "F"));
	}


	@Test
	void testHasPathBfs() {
		assertThat(Graph.hasPathBfs(buildAcyclicGraph(), "A", "F")).isTrue();

		assertThat(Graph.hasPathBfs(buildCyclicGraph(), "A", "F")).isTrue();

		assertThat(Graph.hasPathBfs(buildGraph3(), "F", "J")).isFalse();
		assertThat(Graph.hasPathBfs(buildGraph3(), "F", "H")).isTrue();
		assertThat(Graph.hasPathBfs(buildGraph3(), "F", "K")).isTrue();
		assertThat(Graph.hasPathBfs(buildGraph3(), "F", "I")).isTrue();
		assertThat(Graph.hasPathBfs(buildGraph3(), "F", "G")).isTrue();
		assertThat(Graph.hasPathBfs(buildGraph3(), "J", "H")).isTrue();
		assertThat(Graph.hasPathBfs(buildGraph3(), "J", "F")).isFalse();
		assertThat(Graph.hasPathBfs(buildGraph3(), "J", "J")).isTrue();
	}


	@Test
	void testDepthFirstPrintRecursive() {
		Graph.depthFirstPrintRecursive(buildAcyclicGraph(), "A", null);
		Graph.depthFirstPrintRecursive(buildCyclicGraph(), "A", null);
		System.out.println();
	}


	@Test
	void testHasPathDfsRecursive() {
		assertThat(Graph.hasPathDfsRecursive(buildAcyclicGraph(), "A", "F", null)).isTrue();

		assertThat(Graph.hasPathDfsRecursive(buildCyclicGraph(), "A", "F", null)).isTrue();

		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "J", null)).isFalse();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "H", null)).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "K", null)).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "I", null)).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "G", null)).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "J", "H", null)).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "J", "F", null)).isFalse();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "J", "J", null)).isTrue();
		System.out.println();
	}


	Map<String, List<String>> buildAcyclicGraph() {
		Map<String, List<String>> map = new HashMap<>();
		map.put("A", Arrays.asList("B", "C"));
		map.put("B", Arrays.asList("D"));
		map.put("C", Arrays.asList("E"));
		map.put("D", Arrays.asList("F"));
		map.put("E", Collections.emptyList());
		map.put("F", Collections.emptyList());
		return map;
	}


	Map<String, List<String>> buildCyclicGraph() {
		Map<String, List<String>> map = new HashMap<>();
		map.put("A", Arrays.asList("B", "C"));
		map.put("B", Arrays.asList("D"));
		map.put("C", Arrays.asList("E"));
		map.put("D", Arrays.asList("F"));
		map.put("E", Collections.emptyList());
		// F goes back to A, if the code does not track the visited nodes
		// it will go into an infinite loop
		map.put("F", Arrays.asList("A"));
		return map;
	}


	Map<String, List<String>> buildGraph3() {
		Map<String, List<String>> map = new HashMap<>();
		map.put("F", Arrays.asList("G", "I"));
		map.put("G", Arrays.asList("H"));
		map.put("H", Collections.emptyList());
		map.put("I", Arrays.asList("G", "K"));
		map.put("J", Arrays.asList("I"));
		map.put("K", Collections.emptyList());
		return map;
	}

}