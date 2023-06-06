package com.example.astarsearchvsdfs.model;

import java.util.ArrayList;
import java.util.List;

public class City implements Comparable<City> {
    String name;
    public double latitude;
    public double longitude;
    public List<City> adjacentCities = new ArrayList<>();

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void calculateAdjacentCities(List<Edge> edges) {
        for (Edge edge : edges) {
            if (edge.city1.equals(this) && !adjacentCities.contains(edge.city2)) {
                adjacentCities.add(edge.city2);
            } else if (edge.city2.equals(this) && !adjacentCities.contains(edge.city1)) {
                adjacentCities.add(edge.city1);
            }
        }
    }


    @Override
    public String toString() {
        return name;
    }


    @Override
    public int compareTo(City o) {
        return o.name.compareTo(name);
    }
}