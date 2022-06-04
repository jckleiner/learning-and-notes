package com.example.algorithms;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.example.algorithms.util.Tuple;

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
	void testConnectedComponentCount() {
		assertThat(Graph.connectedComponentCount(buildGraphWithDisconnectedNodes())).isEqualTo(3);
		assertThat(Graph.connectedComponentCount(buildGraphWithDisconnectedNodes2())).isEqualTo(2);
	}


	@Test
	void testLargestComponentCount() {
		assertThat(Graph.largestComponentCount(buildGraphWithDisconnectedNodes())).isEqualTo(5);
		assertThat(Graph.largestComponentCount(buildGraphWithDisconnectedNodes2())).isEqualTo(4);
	}


	@Test
	void testShortestPath() {
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "A")).isEqualTo(0);
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "B")).isEqualTo(1);
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "D")).isEqualTo(2);
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "F")).isEqualTo(3);
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "C")).isEqualTo(1);
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "E")).isEqualTo(2);
		assertThat(Graph.shortestPath(buildAcyclicGraph(), "A", "XYZ")).isEqualTo(-1);

		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "A")).isEqualTo(0);
		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "B")).isEqualTo(1);
		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "D")).isEqualTo(2);
		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "F")).isEqualTo(3);
		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "C")).isEqualTo(1);
		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "E")).isEqualTo(2);
		assertThat(Graph.shortestPath(buildCyclicGraph(), "A", "XYZ")).isEqualTo(-1);
	}


	@Test
	void testShortestPathReturnsPath() {
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "A"))
				.isEqualTo(Arrays.asList("A"));
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "B"))
				.isEqualTo(Arrays.asList("A", "B"));
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "D"))
				.isEqualTo(Arrays.asList("A", "B", "D"));
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "F"))
				.isEqualTo(Arrays.asList("A", "B", "D", "F"));
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "C"))
				.isEqualTo(Arrays.asList("A", "C"));
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "E"))
				.isEqualTo(Arrays.asList("A", "C", "E"));
		assertThat(Graph.shortestPathReturnsPath(buildAcyclicGraph(), "A", "XYZ"))
				.isNull();

		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "A"))
				.isEqualTo(Arrays.asList("A"));
		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "B"))
				.isEqualTo(Arrays.asList("A", "B"));
		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "D"))
				.isEqualTo(Arrays.asList("A", "B", "D"));
		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "F"))
				.isEqualTo(Arrays.asList("A", "B", "D", "F"));
		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "C"))
				.isEqualTo(Arrays.asList("A", "C"));
		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "E"))
				.isEqualTo(Arrays.asList("A", "C", "E"));
		assertThat(Graph.shortestPathReturnsPath(buildCyclicGraph(), "A", "XYZ"))
				.isNull();

		assertThat(Graph.shortestPathReturnsPath(buildGraph3(), "F", "H"))
				.isEqualTo(Arrays.asList("F", "G", "H"));
		assertThat(Graph.shortestPathReturnsPath(buildGraph3(), "J", "H"))
				.isEqualTo(Arrays.asList("J", "I", "G", "H"));
		assertThat(Graph.shortestPathReturnsPath(buildGraph3(), "K", "H"))
				.isNull();

		assertThat(Graph.shortestPathReturnsPath(buildGraphWithDisconnectedNodes(), "1", "2"))
				.isEqualTo(Arrays.asList("1", "2"));
		assertThat(Graph.shortestPathReturnsPath(buildGraphWithDisconnectedNodes(), "4", "7"))
				.isEqualTo(Arrays.asList("4", "6", "7"));
		assertThat(Graph.shortestPathReturnsPath(buildGraphWithDisconnectedNodes(), "8", "4"))
				.isEqualTo(Arrays.asList("8", "6", "4"));
		assertThat(Graph.shortestPathReturnsPath(buildGraphWithDisconnectedNodes(), "3", "6"))
				.isNull();
	}


	@Test
	void testIslandCountMySolution() {
		assertThat(Graph.islandCountMySolution(buildIsland().getFirst())).isEqualTo(5);

		String[][] threeIslands = new String[][] {
				{ "W", "L", "W", "W", "W" },
				{ "W", "L", "W", "L", "L" },
				{ "W", "W", "W", "L", "L" },
				{ "W", "W", "L", "L", "W" },
				{ "L", "L", "W", "L", "W" },
		};
		assertThat(Graph.islandCountMySolution(threeIslands)).isEqualTo(3);

		String[][] twoIslands = new String[][] {
				{ "L", "W", "L" },
				{ "W", "W", "W" }
		};
		assertThat(Graph.islandCountMySolution(twoIslands)).isEqualTo(2);

		String[][] oneIsland = new String[][] {
				{ "L", "L", "L" },
				{ "L", "W", "L" },
				{ "L", "L", "L" }
		};
		assertThat(Graph.islandCountMySolution(oneIsland)).isEqualTo(1);
	}


	@Test
	void testIslandCountAlvinsSolution() {
		assertThat(Graph.islandCountAlvinsSolution(buildIsland().getFirst())).isEqualTo(5);

		String[][] threeIslands = new String[][] {
				{ "W", "L", "W", "W", "W" },
				{ "W", "L", "W", "L", "L" },
				{ "W", "W", "W", "L", "L" },
				{ "W", "W", "L", "L", "W" },
				{ "L", "L", "W", "L", "W" },
		};
		assertThat(Graph.islandCountAlvinsSolution(threeIslands)).isEqualTo(3);

		String[][] twoIslands = new String[][] {
				{ "L", "W", "L" },
				{ "W", "W", "W" }
		};
		assertThat(Graph.islandCountAlvinsSolution(twoIslands)).isEqualTo(2);

		String[][] oneIsland = new String[][] {
				{ "L", "L", "L" },
				{ "L", "W", "L" },
				{ "L", "L", "L" }
		};
		assertThat(Graph.islandCountAlvinsSolution(oneIsland)).isEqualTo(1);
	}


	@Test
	void testIslandMinCount() {
		assertThat(Graph.islandMinCount(buildIsland().getFirst())).isEqualTo(1);

		String[][] threeIslands = new String[][] {
				{ "W", "L", "W", "W", "W" },
				{ "W", "L", "W", "L", "L" },
				{ "W", "W", "W", "L", "L" },
				{ "W", "W", "L", "L", "W" },
				{ "L", "L", "W", "L", "W" },
		};
		assertThat(Graph.islandMinCount(threeIslands)).isEqualTo(2);

		String[][] twoIslands = new String[][] {
				{ "L", "W", "L" },
				{ "W", "W", "W" }
		};
		assertThat(Graph.islandMinCount(twoIslands)).isEqualTo(1);

		String[][] oneIsland = new String[][] {
				{ "L", "L", "L" },
				{ "L", "W", "L" },
				{ "L", "L", "L" }
		};
		assertThat(Graph.islandMinCount(oneIsland)).isEqualTo(8);
	}


	@Test
	void testTransform2dArrayIntoAdjacencyList() {
		assertThat(Graph.transform2dArrayIntoAdjacencyList(buildIsland().getFirst()))
				.isEqualTo(buildIsland().getSecond());
	}


	@Test
	void testClosestCarrot() {
		String[][] grid = new String[][] {
				{ "O", "O", "O", "O", "O" },
				{ "O", "X", "O", "O", "O" },
				{ "O", "X", "X", "O", "O" },
				{ "O", "X", "C", "O", "O" },
				{ "O", "X", "X", "O", "O" },
				{ "C", "O", "O", "O", "O" }
		};
		assertThat(Graph.closestCarrot(grid, 0, 0)).isEqualTo(5);
		assertThat(Graph.closestCarrot(grid, 1, 2)).isEqualTo(4);
		assertThat(Graph.closestCarrot(grid, 3, 2)).isEqualTo(0);
		assertThat(Graph.closestCarrot(grid, 0, 4)).isEqualTo(5);

		String[][] grid2 = new String[][] {
				{ "O", "O", "O", "O", "O" },
				{ "O", "O", "O", "O", "O" },
				{ "O", "O", "O", "O", "O" },
				{ "O", "O", "O", "O", "O" },
				{ "O", "O", "O", "X", "X" },
				{ "O", "O", "O", "X", "C" }
		};
		assertThat(Graph.closestCarrot(grid2, 0, 0)).isEqualTo(-1);
		assertThat(Graph.closestCarrot(grid2, 0, 4)).isEqualTo(-1);
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


	Tuple<String[][], Map<String, Set<String>>> buildIsland() {
		String[][] island = new String[][] {
				{ "W", "L", "W", "W", "W" },
				{ "W", "L", "W", "L", "L" },
				{ "W", "W", "W", "L", "L" },
				{ "W", "W", "L", "W", "W" },
				{ "L", "L", "W", "L", "W" },
		};

		Map<String, Set<String>> expected = new HashMap<>();
		expected.put("0,1", new HashSet<>(Arrays.asList("1,1")));
		expected.put("1,1", new HashSet<>(Arrays.asList("0,1")));
		expected.put("1,3", new HashSet<>(Arrays.asList("1,4", "2,3")));
		expected.put("1,4", new HashSet<>(Arrays.asList("1,3", "2,4")));
		expected.put("2,3", new HashSet<>(Arrays.asList("1,3", "2,4")));
		expected.put("2,4", new HashSet<>(Arrays.asList("1,4", "2,3")));
		expected.put("3,2", new HashSet<>());
		expected.put("4,0", new HashSet<>(Arrays.asList("4,1")));
		expected.put("4,1", new HashSet<>(Arrays.asList("4,0")));
		expected.put("4,3", new HashSet<>());

		return new Tuple<>(island, expected);
	}

}