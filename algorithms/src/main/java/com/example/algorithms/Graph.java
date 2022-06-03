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


	public static boolean hasPathBfs(Map<String, List<String>> graph, String srcNode, String targetNode) {
		Queue<String> q = new LinkedList<>();
		Map<String, Boolean> visitedNodes = new HashMap<>();
		q.add(srcNode);

		while (!q.isEmpty()) {
			String current = q.poll();

			// if node was already visited
			if (visitedNodes.get(current) != null) {
				continue;
			}

			if (targetNode.equals(current)) {
				return true;
			}

			q.addAll(graph.get(current));
			visitedNodes.put(current, true);
		}

		return false;
	}


	public static boolean hasPathDfsRecursive(Map<String, List<String>> graph,
			String srcNode,
			String targetNode,
			Map<String, Boolean> nodesWithPathToTarget) {

		if (nodesWithPathToTarget == null) {
			nodesWithPathToTarget = new HashMap<>();
		}

		if (srcNode.equals(targetNode)) {
			return true;
		}

		for (String neighbour : graph.get(srcNode)) {
			boolean hasNeighbourPath = hasPathDfsRecursive(graph, neighbour, targetNode, nodesWithPathToTarget);
			nodesWithPathToTarget.put(srcNode, hasNeighbourPath);

			if (hasNeighbourPath) {
				return true;
			}
		}

		// if node has no neighbours
		if (graph.get(srcNode).isEmpty()) {
			nodesWithPathToTarget.put(srcNode, false);
		}

		return nodesWithPathToTarget.get(srcNode);
	}


	// the call stack can be used as our stack to go deeper
	public static void depthFirstPrintRecursive(Map<String, List<String>> graph, String node, Map<String, Boolean> visitedNodes) {
		// this function does not have an explicit base case
		// the implicit base case is a node with no neighbours

		if (visitedNodes == null) {
			visitedNodes = new HashMap<>();
		}

		if (visitedNodes.get(node) != null) {
			// node already visited
			return;
		}

		System.out.println(node);

		List<String> neighbours = graph.get(node);
		visitedNodes.put(node, true);

		for (String n : neighbours) {
			depthFirstPrintRecursive(graph, n, visitedNodes);
		}
	}
}
