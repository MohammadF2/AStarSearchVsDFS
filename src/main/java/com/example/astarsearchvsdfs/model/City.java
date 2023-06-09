package com.example.astarsearchvsdfs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City implements Comparable<City> {
    String name;
    private double latitude;
    private double longitude;
    private List<City> adjacentCities = new ArrayList<>();
    private Map<City, Double> hur;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hur = new HashMap<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<City> getAdjacentCities() {
        return adjacentCities;
    }

    public void setAdjacentCities(List<City> adjacentCities) {
        this.adjacentCities = adjacentCities;
    }

    public Map<City, Double> getHur() {
        return hur;
    }

    public void setHur(Map<City, Double> hur) {
        this.hur = hur;
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