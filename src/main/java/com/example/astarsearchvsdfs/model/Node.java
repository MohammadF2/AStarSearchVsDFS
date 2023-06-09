package com.example.astarsearchvsdfs.model;

public class Node {

    public City city;
    public Node parent;
    public double gScore;
    public double hScore;

    public Node(City city, Node parent, double gScore, double hScore) {
        this.city = city;
        this.parent = parent;
        this.gScore = gScore;
        this.hScore = hScore;
    }
}
