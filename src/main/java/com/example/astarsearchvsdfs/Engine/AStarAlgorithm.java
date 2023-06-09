package com.example.astarsearchvsdfs.Engine;

import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;
import com.example.astarsearchvsdfs.model.Node;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class AStarAlgorithm {

    public List<City> aStar(City start, City goal,  List<Edge> edges, Map<String, City> cities) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.gScore + node.hScore));
        openList.add(new Node(start, null, 0, getHeuristic(start, goal, cities)));

        Map<City, Node> allNodes = new HashMap<>();
        allNodes.put(start, openList.peek());
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.city.equals(goal)) {
                return constructPath(current);
            }


            current.city.calculateAdjacentCities(edges);
            for (City adjacentCity : current.city.getAdjacentCities()) {
                Node adjacent = new Node(adjacentCity, current, current.gScore + getDistance(current.city, adjacentCity, edges), getHeuristic(adjacentCity, goal, cities));
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

    private double getHeuristic(City city1, City city2, Map<String, City> cities) {
        // get heuristic data
        if(!cities.get(city1.getName()).getHur().containsKey(city2))
            return 0;

        return cities.get(city1.getName()).getHur().get(city2);
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