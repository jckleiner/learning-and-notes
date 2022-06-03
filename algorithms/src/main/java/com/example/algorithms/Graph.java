package com.example.algorithms;

import java.util.*;


public class Graph {

	public static List<String> breadthFirstPrint(Map<String, List<String>> graph) {
		// queue
		Queue<String> q = new LinkedList<>();
		List<String> nodesVisited = new ArrayList<>();

		// start node -> A
		q.add("A");

		while (!q.isEmpty()) {
			// pop the first node in the queue
			String current = q.poll();
			nodesVisited.add(current);
			System.out.println(current);
			// add all the nodes neighbours to the queue
			List<String> neighbours = graph.get(current);
			q.addAll(neighbours);
		}

		return nodesVisited;
	}


	public static List<String> depthFirstPrint(Map<String, List<String>> graph) {
		// stack
		Stack<String> stack = new Stack<>();
		List<String> nodesVisited = new ArrayList<>();

		// start node -> A
		stack.add("A");

		while (!stack.isEmpty()) {
			String current = stack.pop();
			nodesVisited.add(current);
			System.out.println(current);
			List<String> neighbours = graph.get(current);
			stack.addAll(neighbours);
		}

		System.out.println();
		return nodesVisited;
	}


	public static List<String> depthFirstPrintRecursive(Map<String, List<String>> graph) {
		// stack
		Stack<String> stack = new Stack<>();

		// start node -> A
		stack.add("A");

		while (!stack.isEmpty()) {
			String current = stack.pop();
			System.out.println(current);
			List<String> neighbours = graph.get(current);
			stack.addAll(neighbours);
		}
		System.out.println();
		return null;
	}
}
