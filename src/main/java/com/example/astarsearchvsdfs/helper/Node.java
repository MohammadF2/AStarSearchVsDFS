package com.example.astarsearchvsdfs.helper;

import com.example.astarsearchvsdfs.model.City;

public class Node {

    City city;
    Node parent;
    double gScore;
    double hScore;

    public Node(City city, Node parent, double gScore, double hScore) {
        this.city = city;
        this.parent = parent;
        this.gScore = gScore;
        this.hScore = hScore;
    }
}
