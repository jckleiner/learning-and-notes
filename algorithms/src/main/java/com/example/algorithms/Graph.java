package com.example.algorithms;

import java.util.*;


public class Graph {

	public static List<String> breadthFirstPrint(Map<String, List<String>> graph, String startNode) {
		// queue
		Queue<String> q = new LinkedList<>();
		// instant lookup is important.
		// If we use a list, we need to traverse the list each time to find if a node was visited
		Map<String, Boolean> nodesVisited = new HashMap<>();
		// used only for testing
		List<String> nodesVisitedOrdered = new ArrayList<>();

		// start node -> A
		q.add(startNode);

		while (!q.isEmpty()) {
			// pop the first node in the queue
			String current = q.poll();

			if (nodesVisited.get(current) != null) {
				continue;
			}

			nodesVisited.put(current, true);
			nodesVisitedOrdered.add(current);
			System.out.println(current);

			// add all the neighbours to the queue
			q.addAll(graph.get(current));
		}

		return nodesVisitedOrdered;
	}


	public static List<String> depthFirstPrint(Map<String, List<String>> graph, String startNode) {
		// stack
		Stack<String> stack = new Stack<>();
		// instant lookup is important.
		// If we use a list, we need to traverse the list each time to find if a node was visited
		Map<String, Boolean> nodesVisited = new HashMap<>();
		// used only for testing
		List<String> nodesVisitedOrdered = new ArrayList<>();

		// start node -> A
		stack.add(startNode);

		while (!stack.isEmpty()) {
			String current = stack.pop();

			// if node was visited before, skip the iteration
			if (nodesVisited.get(current) != null) {
				continue;
			}

			nodesVisited.put(current, true);
			nodesVisitedOrdered.add(current);
			System.out.println(current);

			// add the neighbours to the stack
			stack.addAll(graph.get(current));
		}

		System.out.println();
		return nodesVisitedOrdered;
	}


	// the call stack can be used as our stack to go deeper
	// Cyclic graphs are not checked for here
	public static void depthFirstPrintRecursive(Map<String, List<String>> graph, String node, Map<String, Boolean> visitedNodes) {
		// this function does not have an explicit base case
		// the implicit base case is a node with no neighbours

		if (visitedNodes == null) {
			visitedNodes = new HashMap<>();
		}

		if (visitedNodes.get(node) != null) {
			return; // node already visited
		}

		System.out.println(node);

		List<String> neighbours = graph.get(node);
		visitedNodes.put(node, true);

		for (String n : neighbours) {
			depthFirstPrintRecursive(graph, n, visitedNodes);
		}
	}


	public static boolean hasPathBfs(Map<String, List<String>> graph, String srcNode, String targetNode) {
		Queue<String> q = new LinkedList<>();
		Map<String, Boolean> visitedNodes = new HashMap<>();
		q.add(srcNode);

		while (!q.isEmpty()) {
			String current = q.poll();

			if (visitedNodes.getOrDefault(current, false)) {
				continue; // if node was already visited
			}
			visitedNodes.put(current, true); // mark as visited

			if (targetNode.equals(current)) {
				return true; // we found the targetNode
			}

			// doing this would add all neighbours of a node, regardless if it was visited or not
			// makes unnecessary calls to already visited nodes
			// q.addAll(graph.get(current));

			// add all the neighbours which were not visited
			for (String neighbour : graph.get(current)) {
				if (!visitedNodes.getOrDefault(neighbour, false)) {
					q.add(neighbour);
				}
			}

		}

		return false;
	}


	public static boolean hasPathDfsRecursive(Map<String, List<String>> graph,
			String srcNode,
			String targetNode,
			Map<String, Boolean> visitedNodes) {

		if (srcNode.equals(targetNode)) {
			return true; // found the target
		}

		if (visitedNodes.getOrDefault(srcNode, false)) {
			return false; // already visited
		}

		visitedNodes.put(srcNode, true); // mark as visited

		// go deeper into the non-visited neighbours
		for (String neighbour : graph.get(srcNode)) {
			if (visitedNodes.getOrDefault(neighbour, false)) {
				continue; // skip if neighbour was visited
			}
			if (hasPathDfsRecursive(graph, neighbour, targetNode, visitedNodes)) {
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
}
