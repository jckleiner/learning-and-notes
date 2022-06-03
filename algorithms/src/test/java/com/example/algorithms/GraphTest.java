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
		// A -> B -> D -> F -> C -> E
		Graph.depthFirstPrintRecursive(buildAcyclicGraph(), "A", null);
		System.out.println();
		// A -> B -> D -> F -> C -> E
		Graph.depthFirstPrintRecursive(buildCyclicGraph(), "A", null);
		System.out.println();
	}


	@Test
	void testHasPathDfsRecursive() {
		assertThat(Graph.hasPathDfsRecursive(buildAcyclicGraph(), "A", "F", new HashSet<>())).isTrue();

		assertThat(Graph.hasPathDfsRecursive(buildCyclicGraph(), "A", "F", new HashSet<>())).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildCyclicGraph(), "A", "WFQDSA", new HashSet<>())).isFalse();

		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "J", new HashSet<>())).isFalse();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "H", new HashSet<>())).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "K", new HashSet<>())).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "I", new HashSet<>())).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "F", "G", new HashSet<>())).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "J", "H", new HashSet<>())).isTrue();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "J", "F", new HashSet<>())).isFalse();
		assertThat(Graph.hasPathDfsRecursive(buildGraph3(), "J", "J", new HashSet<>())).isTrue();
		System.out.println();
	}


	@Test
	void testConvertUndirectedEdgeListToAdjacencyList() {
		String[][] input = new String[][] {
				{ "i", "j" },
				{ "k", "i" },
				{ "m", "k" },
				{ "k", "l" },
				{ "o", "n" },
		};

		Map<String, List<String>> expected = new HashMap<>();
		expected.put("i", new ArrayList<>(Arrays.asList("j", "k")));
		expected.put("j", new ArrayList<>(Collections.singleton("i")));
		expected.put("k", new ArrayList<>(Arrays.asList("i", "m", "l")));
		expected.put("m", new ArrayList<>(Collections.singleton("k")));
		expected.put("l", new ArrayList<>(Collections.singleton("k")));
		expected.put("o", new ArrayList<>(Collections.singleton("n")));
		expected.put("n", new ArrayList<>(Collections.singleton("o")));

		assertThat(Graph.convertUndirectedEdgeListToAdjacencyList(input)).isEqualTo(expected);
	}


	@Test
	void connectedComponentCount() {
		assertThat(Graph.connectedComponentCount(buildGraphWithDisconnectedNodes())).isEqualTo(3);
		assertThat(Graph.connectedComponentCount(buildGraphWithDisconnectedNodes2())).isEqualTo(2);
	}


	@Test
	void largestComponentCount() {
		assertThat(Graph.largestComponentCount(buildGraphWithDisconnectedNodes())).isEqualTo(5);
		assertThat(Graph.largestComponentCount(buildGraphWithDisconnectedNodes2())).isEqualTo(4);
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


	Map<String, List<String>> buildGraphWithDisconnectedNodes() {
		Map<String, List<String>> map = new HashMap<>();
		map.put("1", Arrays.asList("2"));
		map.put("2", Arrays.asList("1"));
		map.put("4", Arrays.asList("6"));
		map.put("5", Arrays.asList("6"));
		map.put("7", Arrays.asList("6"));
		map.put("8", Arrays.asList("6"));
		map.put("6", Arrays.asList("4", "5", "7", "8"));
		map.put("3", Collections.emptyList());
		return map;
	}


	Map<String, List<String>> buildGraphWithDisconnectedNodes2() {
		Map<String, List<String>> map = new HashMap<>();
		map.put("0", Arrays.asList("8", "1", "5"));
		map.put("1", Arrays.asList("0"));
		map.put("5", Arrays.asList("0", "8"));
		map.put("8", Arrays.asList("0", "5"));
		map.put("2", Arrays.asList("3", "4"));
		map.put("3", Arrays.asList("2", "4"));
		map.put("4", Arrays.asList("3", "2"));
		return map;
	}

}