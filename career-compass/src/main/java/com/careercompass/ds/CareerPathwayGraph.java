package com.careercompass.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CareerPathwayGraph {
    private final Map<String, List<String>> adjacencyList = new HashMap<>();

    public void addEdge(String from, String to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    // Breadth-First Search (BFS) to explore pathway steps
    public List<String> getConnectedPathways(String startNode) {
        List<String> visitedOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startNode);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (!current.equals(startNode)) {
                visitedOrder.add(current);
            }

            for (String neighbor : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return visitedOrder;
    }
}