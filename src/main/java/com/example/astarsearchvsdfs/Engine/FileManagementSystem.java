package com.example.astarsearchvsdfs.Engine;

import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManagementSystem {

    Map<String, City> cities = new HashMap<>();
    List<Edge> edges = new ArrayList<>();
    private Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void readCitiesFromFile() throws Exception {
        try {

            List<String> citiesFile = Files.readAllLines(Paths.get("C:\\Users\\frajm\\OneDrive\\سطح المكتب\\res\\data\\Cities.csv"));
            List<String> roadsFile = Files.readAllLines(Paths.get("C:\\Users\\frajm\\OneDrive\\سطح المكتب\\res\\data\\Roads.csv"));
            List<String> airDisFile = Files.readAllLines(Paths.get("C:\\Users\\frajm\\OneDrive\\سطح المكتب\\res\\data\\AirDistance.csv"));
            String[] parser;

            for (String s : citiesFile) { // read's the city's from the file
                parser = s.split(",");
                cities.put(parser[0].trim(), new City(parser[0].trim(), Double.parseDouble(parser[1].trim()), Double.parseDouble(parser[2].trim())));
            }
            for (String s : roadsFile) { // read's the edges from the file
                parser = s.split(",");

                if (!cities.containsKey(parser[0].trim()) || !cities.containsKey(parser[1].trim())) // check if the cities exists on the map
                    continue;

                edges.add(new Edge(cities.get(parser[0].trim()), cities.get(parser[1].trim()), Double.parseDouble(parser[2].trim())));
            }
            for (String s : airDisFile) { // read's the Heuristic data from the file
                parser = s.split(",");

                if (!cities.containsKey(parser[0].trim()) || !cities.containsKey(parser[1].trim())) // check if the cities exists on the map
                    continue;

                City c1 = cities.get(parser[0].trim());
                City c2 = cities.get(parser[1].trim());
                try {
                    if(c1.getHur().containsKey(cities.get(parser[1].trim()))) // check if it's already added to the heuristic function
                        continue;
                    c1.getHur().put(c2, Double.parseDouble(parser[2].trim()));
                    c2.getHur().put(c1, Double.parseDouble(parser[2].trim()));
                    System.out.println("size " + c1.getHur().size() + " " + c2.getHur().size());
                    System.out.println("New air distance: " + parser[0].trim() + " and " + parser[1].trim() + " of distance " + parser[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("An error happened with city: " + parser[0].trim() + " and " + parser[1].trim() + " of distance " + parser[2].trim());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Map<String, City> getCities() {
        return cities;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
