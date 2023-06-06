package com.example.astarsearchvsdfs.helper;

import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class AStarAlgorithm {

    public List<City> aStar(City start, City goal,  List<Edge> edges) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.gScore + node.hScore));
        openList.add(new Node(start, null, 0, getHeuristic(start, goal)));

        Map<City, Node> allNodes = new HashMap<>();
        allNodes.put(start, openList.peek());

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.city.equals(goal)) {
                return constructPath(current);
            }

            current.city.calculateAdjacentCities(edges);
            for (City adjacentCity : current.city.adjacentCities) {
                Node adjacent = new Node(adjacentCity, current, current.gScore + getDistance(current.city, adjacentCity, edges), getHeuristic(adjacentCity, goal));
                if (!allNodes.containsKey(adjacentCity) || adjacent.gScore < allNodes.get(adjacentCity).gScore) {
                    allNodes.put(adjacentCity, adjacent);
                    openList.add(adjacent);
                }
            }
        }

        return null;
    }

    private List<City> constructPath(Node node) {
        List<City> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.city);
            node = node.parent;
        }
        return path;
    }

    private double getHeuristic(City city1, City city2) {
        // Use Euclidean distance for the heuristic
        return Math.sqrt(Math.pow(city1.latitude - city2.latitude, 2) + Math.pow(city1.longitude - city2.longitude, 2));
    }

    public static double getDistance(City city1, City city2, List<Edge> edges) {
        AtomicReference<Double> dis = new AtomicReference<>((double) 0);
        edges.forEach(edge -> {
            if(edge.getCity1().compareTo(city1) == 0 && edge.getCity2().compareTo(city2) == 0) {
                dis.set(edge.getDistance());
            }
        });
        return dis.get();
    }
}