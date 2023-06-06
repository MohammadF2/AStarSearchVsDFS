package com.example.astarsearchvsdfs.helper;

import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

    public void openFileChooser() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open City File");

        // Add filter to allow only .txt files to be selected.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            readCitiesFromFile(file.getAbsolutePath());
        }
    }

    public void readCitiesFromFile(String filePath) throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            int numberOfCities = Integer.parseInt(lines.get(0));

            for (int i = 1; i < numberOfCities-1; i++) {
                String[] cityData = lines.get(i).split(",");
                String cityName = cityData[0].trim();
                System.out.println(cityData[1].trim() + " " + cityData[2].trim());
                double latitude = Double.parseDouble(cityData[1].trim());
                double longitude = Double.parseDouble(cityData[2].trim());
                cities.put(cityName, new City(cityName, latitude, longitude));
            }

            for (int i = numberOfCities - 1; i < lines.size(); i++) {
                String[] cityPair = lines.get(i).split(",");
                City city1 = cities.get(cityPair[0].trim());
                City city2 = cities.get(cityPair[1].trim());
                if (city1 != null && city2 != null) {
                    edges.add(new Edge(city1, city2, Double.parseDouble(cityPair[2].trim())));
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
