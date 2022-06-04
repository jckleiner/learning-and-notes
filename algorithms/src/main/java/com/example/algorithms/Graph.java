package com.example.algorithms;

import java.util.*;

import com.example.algorithms.util.Tuple;


public class Graph {

	public static List<String> breadthFirstPrint(Map<String, List<String>> graph, String startNode) {
		// queue
		Queue<String> q = new LinkedList<>();
		// instant lookup is important. HashSet has O(1) insertion and look up
		// If we use a list, we need to traverse the list each time to find if a node was visited
		Set<String> nodesVisited = new HashSet<>();
		// used only for testing
		List<String> nodesVisitedInOrder = new ArrayList<>();

		// start node -> A
		q.add(startNode);

		while (!q.isEmpty()) {
			// pop the first node in the queue
			String current = q.poll();

			if (nodesVisited.contains(current)) {
				continue;
			}

			nodesVisited.add(current);
			nodesVisitedInOrder.add(current);

			// System.out.println(current);

			// add all the neighbours to the queue
			q.addAll(graph.get(current));
		}

		return nodesVisitedInOrder;
	}


	public static List<String> depthFirstPrint(Map<String, List<String>> graph, String startNode) {
		// stack
		Stack<String> stack = new Stack<>();
		// instant lookup is important. HashSet has O(1) insertion and look up
		// If we use a list, we need to traverse the list each time to find if a node was visited
		Set<String> nodesVisited = new HashSet<>();
		// used only for testing
		List<String> nodesVisitedOrdered = new ArrayList<>();

		// start node -> A
		stack.add(startNode);

		while (!stack.isEmpty()) {
			String current = stack.pop();

			// if node was visited before, skip the iteration
			if (nodesVisited.contains(current)) {
				continue;
			}

			nodesVisited.add(current);
			nodesVisitedOrdered.add(current);

			// System.out.println(current);

			// add the neighbours to the stack
			stack.addAll(graph.get(current));
		}

		System.out.println();
		return nodesVisitedOrdered;
	}


	// the call stack can be used as our stack to go deeper
	// Cyclic graphs are not checked for here
	public static void depthFirstPrintRecursive(Map<String, List<String>> graph, String node, Set<String> nodesVisited) {
		// this function does not have an explicit base case
		// the implicit base case is a node with no neighbours

		if (nodesVisited == null) {
			nodesVisited = new HashSet<>();
		}

		if (nodesVisited.contains(node)) {
			return; // node already visited
		}
		nodesVisited.add(node);

		System.out.println(node);

		for (String neighbour : graph.get(node)) {
			if (!nodesVisited.contains(neighbour)) {
				depthFirstPrintRecursive(graph, neighbour, nodesVisited);
			}
		}
	}


	public static boolean hasPathBfs(Map<String, List<String>> graph, String srcNode, String targetNode) {
		Queue<String> q = new LinkedList<>();
		Set<String> nodesVisited = new HashSet<>();
		q.add(srcNode);

		while (!q.isEmpty()) {
			String current = q.poll();

			if (nodesVisited.contains(current)) {
				continue; // if node was already visited
			}
			nodesVisited.add(current); // mark as visited

			if (targetNode.equals(current)) {
				return true; // we found the targetNode
			}

			// doing this would add all neighbours of a node, regardless if it was visited or not
			// makes unnecessary calls to already visited nodes
			// q.addAll(graph.get(current));

			// add all the neighbours which were not visited
			for (String neighbour : graph.get(current)) {
				if (!nodesVisited.contains(neighbour)) {
					q.add(neighbour);
				}
			}

		}

		return false;
	}


	public static boolean hasPathDfsRecursive(Map<String, List<String>> graph,
			String srcNode,
			String targetNode,
			Set<String> nodesVisited) {

		if (srcNode.equals(targetNode)) {
			return true; // found the target
		}

		if (nodesVisited.contains(srcNode)) {
			return false; // already visited
		}

		nodesVisited.add(srcNode); // mark as visited

		// go deeper into the non-visited neighbours
		for (String neighbour : graph.get(srcNode)) {
			if (nodesVisited.contains(neighbour)) {
				continue; // skip if neighbour was visited
			}
			if (hasPathDfsRecursive(graph, neighbour, targetNode, nodesVisited)) {
				return true;
			}
		}

		return false;
	}


	// for undirected graphs
	public static Map<String, List<String>> convertUndirectedEdgeListToAdjacencyList(String[][] edgeArr) {

		Map<String, List<String>> result = new HashMap<>();

		for (String[] arr : edgeArr) {
			// first
			List<String> neighboursFirst = result.get(arr[0]);
			if (neighboursFirst == null) {
				neighboursFirst = new ArrayList<>();
			}
			neighboursFirst.add(arr[1]);
			result.put(arr[0], neighboursFirst);

			// second
			List<String> neighboursSecond = result.get(arr[1]);
			if (neighboursSecond == null) {
				neighboursSecond = new ArrayList<>();
			}
			neighboursSecond.add(arr[0]);
			result.put(arr[1], neighboursSecond);
		}

		System.out.println(result);
		return result;
	}


	public static int connectedComponentCount(Map<String, List<String>> graph) {
		List<String> allNodes = new ArrayList<>(graph.keySet());
		Queue<String> q = new LinkedList<>();
		int componentCount = 0;

		while (!allNodes.isEmpty()) {
			// BFS and mark all visited nodes
			q.add(allNodes.get(0));
			Set<String> visited = new HashSet<>();

			while (!q.isEmpty()) {
				String current = q.poll();
				if (visited.contains(current)) {
					continue;
				}
				visited.add(current);
				q.addAll(graph.get(current));
			}

			// remove all visited nodes from the collection
			allNodes.removeAll(visited);
			componentCount++;
		}

		return componentCount;
	}


	public static int largestComponentCount(Map<String, List<String>> graph) {
		List<String> allNodes = new ArrayList<>(graph.keySet());
		Queue<String> q = new LinkedList<>();
		int maxCount = 0;

		while (!allNodes.isEmpty()) {
			// BFS and mark all visited nodes
			q.add(allNodes.get(0));
			Set<String> visited = new HashSet<>();

			while (!q.isEmpty()) {
				String current = q.poll();
				if (visited.contains(current)) {
					continue;
				}
				visited.add(current);
				q.addAll(graph.get(current));
			}

			// remove all visited nodes from the collection
			allNodes.removeAll(visited);
			maxCount = Math.max(maxCount, visited.size());
		}

		return maxCount;
	}


	public static int shortestPath(Map<String, List<String>> adjList, String srcNode, String destNode) {
		Queue<Tuple<String, Integer>> q = new LinkedList<>();
		Set<String> nodesVisited = new HashSet<>();
		q.add(new Tuple<>(srcNode, 0));

		while (!q.isEmpty()) {
			Tuple<String, Integer> current = q.poll();

			if (current.getFirst().equals(destNode)) {
				return current.getSecond();
			}

			if (nodesVisited.contains(current.getFirst())) {
				continue;
			}

			nodesVisited.add(current.getFirst());

			for (String neighbour : adjList.get(current.getFirst())) {
				if (!nodesVisited.contains(neighbour)) {
					q.add(new Tuple<>(neighbour, current.getSecond() + 1));
				}
			}
		}

		return -1;
	}


	public static List<String> shortestPathReturnsPath(Map<String, List<String>> adjList, String srcNode, String destNode) {
		Queue<String> q = new LinkedList<>();
		Set<String> nodesVisited = new HashSet<>();
		q.add(srcNode);

		Map<String, String> childToParentMapping = new HashMap<>();
		childToParentMapping.put(srcNode, null);

		while (!q.isEmpty()) {
			String current = q.poll();

			if (current.equals(destNode)) {
				// backtrack to find the path
				List<String> path = new ArrayList<>();
				String node = current;

				while (node != null) {
					path.add(node);
					node = childToParentMapping.get(node);
				}

				Collections.reverse(path);

				return path;
			}

			if (nodesVisited.contains(current)) {
				continue;
			}

			nodesVisited.add(current);

			for (String neighbour : adjList.get(current)) {
				if (!nodesVisited.contains(neighbour)) {
					childToParentMapping.put(neighbour, current);
					q.add(neighbour);
				}
			}
		}

		return null;
	}


	public static int islandCountMySolution(String[][] island) {
		// transform island into a graph
		Map<String, Set<String>> graph = transform2dArrayIntoAdjacencyList(island);

		// BFS and count the components/islands
		List<String> allNodes = new ArrayList<>(graph.keySet());
		int islandCount = 0;

		while (!allNodes.isEmpty()) {
			String startNode = allNodes.get(0);
			Queue<String> q = new LinkedList<>();
			q.add(startNode);
			Set<String> nodesVisited = new HashSet<>();

			// BFS
			while (!q.isEmpty()) {
				String current = q.poll();

				if (nodesVisited.contains(current)) {
					continue;
				}
				nodesVisited.add(current);

				// unvisited neighbours
				for (String neighbour : graph.get(current)) {
					if (!nodesVisited.contains(neighbour)) {
						q.add(neighbour);
					}
				}
			}
			allNodes.removeAll(nodesVisited);
			islandCount++;
		}

		return islandCount;
	}


	public static Map<String, Set<String>> transform2dArrayIntoAdjacencyList(String[][] island) {
		Map<String, Set<String>> graph = new HashMap<>();

		System.out.println("island: " + Arrays.deepToString(island));

		for (int i = 0; i < island.length; i++) {
			for (int j = 0; j < island[0].length; j++) {

				if (!island[i][j].equals("L")) {
					continue;
				}

				String currentCellKey = i + "," + j;
				System.out.println("found L: " + currentCellKey);

				if (graph.get(currentCellKey) == null) {
					graph.put(currentCellKey, new HashSet<>());
				}

				// right
				if (j + 1 < island[i].length) {

					if (island[i][j + 1].equals("L")) {
						String rightCellKey = i + "," + (j + 1);
						if (graph.get(rightCellKey) == null) {
							graph.put(rightCellKey, new HashSet<>());
						}
						graph.get(rightCellKey).add(currentCellKey);
						graph.get(currentCellKey).add(rightCellKey);
					}
				}

				// bottom
				if (i + 1 < island.length) {

					if (island[i + 1][j].equals("L")) {
						String bottomCellKey = (i + 1) + "," + (j);
						// TODO can be removed
						if (graph.get(bottomCellKey) == null) {
							graph.put(bottomCellKey, new HashSet<>());
						}
						graph.get(bottomCellKey).add(currentCellKey);
						graph.get(currentCellKey).add(bottomCellKey);
					}

				}
			}
		}

		return graph;
	}


	public static int islandCountAlvinsSolution(String[][] island) {
		Set<String> nodesVisited = new HashSet<>();
		int islandCount = 0;

		for (int i = 0; i < island.length; i++) {
			for (int j = 0; j < island[0].length; j++) {
				if (!island[i][j].equals("L")) {
					continue; // not island
				}
				if (nodesVisited.contains(i + "," + j)) {
					continue; // already visited
				}

				// since this node was not visited before, we are starting a new bfs
				// which means a new island
				islandCount++;

				// BFS
				Queue<String> q = new LinkedList<>();
				q.add(i + "," + j);

				while (!q.isEmpty()) {
					String current = q.poll();
					int currentRow = Integer.parseInt(current.split(",")[0]);
					int currentColumn = Integer.parseInt(current.split(",")[1]);

					if (nodesVisited.contains(current)) {
						continue;
					}
					nodesVisited.add(current);

					/* 	add unvisited neighbours to the queue
						a neighbour is defined as:
							1. Should be up/left/right/down to the current cell
							2. Should not be visited yet
							3. Should be "L" (land)
					*/
					// left
					String leftCellKey = currentRow + "," + (currentColumn - 1);
					if (currentColumn - 1 > 0
							&& island[currentRow][currentColumn - 1].equals("L")
							&& !nodesVisited.contains(leftCellKey)) {
						q.add(leftCellKey);
					}

					// right
					String rightCellKey = currentRow + "," + (currentColumn + 1);
					if (currentColumn + 1 < island[currentRow].length
							&& island[currentRow][currentColumn + 1].equals("L")
							&& !nodesVisited.contains(rightCellKey)) {
						q.add(rightCellKey);
					}

					// up
					String upCellKey = (currentRow + 1) + "," + currentColumn;
					if (currentRow + 1 < island.length
							&& island[currentRow + 1][currentColumn].equals("L")
							&& !nodesVisited.contains(upCellKey)) {
						q.add(upCellKey);
					}

					// down
					String downCellKey = (currentRow - 1) + "," + currentColumn;
					if (currentRow - 1 > 0
							&& island[currentRow - 1][currentColumn].equals("L")
							&& !nodesVisited.contains(downCellKey)) {
						q.add(downCellKey);
					}
				}

			}
		}

		return islandCount;
	}
}
