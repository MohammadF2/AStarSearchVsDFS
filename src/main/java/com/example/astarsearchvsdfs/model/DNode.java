package com.example.astarsearchvsdfs.model;

public class DNode {
    public City city;
    public DNode parent;

    public DNode(City city, DNode parent) {
        this.city = city;
        this.parent = parent;
    }

}
