package com.example.astarsearchvsdfs.helper;

import com.example.astarsearchvsdfs.model.City;

public class DNode {
    City city;
    DNode parent;

    public DNode(City city, DNode parent) {
        this.city = city;
        this.parent = parent;
    }

}
