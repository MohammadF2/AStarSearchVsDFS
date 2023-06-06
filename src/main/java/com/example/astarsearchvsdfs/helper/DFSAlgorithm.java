package com.example.astarsearchvsdfs.helper;

import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;

import java.util.*;

public class DFSAlgorithm {

    public List<City> dfs(City start, City goal, List<Edge> edges) {
        Stack<DNode> stack = new Stack<>();
        stack.push(new DNode(start, null));

        Map<City, DNode> visitedNodes = new HashMap<>();
        visitedNodes.put(start, stack.peek());

        while (!stack.isEmpty()) {
            DNode current = stack.pop();
            if (current.city.equals(goal)) {
                return constructPath(current);
            }

            current.city.calculateAdjacentCities(edges);
            for (City adjacentCity : current.city.adjacentCities) {
                if (!visitedNodes.containsKey(adjacentCity)) {
                    DNode adjacent = new DNode(adjacentCity, current);
                    visitedNodes.put(adjacentCity, adjacent);
                    stack.push(adjacent);
                }
            }
        }

        return null;
    }

    private List<City> constructPath(DNode node) {
        List<City> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.city);
            node = node.parent;
        }
        return path;
    }

}
