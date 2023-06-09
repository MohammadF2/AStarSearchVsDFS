package com.example.astarsearchvsdfs.model;

public class Edge {
    City city1;
    City city2;
    double distance;

    public Edge(City city1, City city2, double distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return city1.name + " " + city2.name;
    }

}