package com.example.astarsearchvsdfs;

import com.example.astarsearchvsdfs.helper.FileManagementSystem;
import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainSceneController {



    @FXML
    private Button bothB;

    public static Map<String, City> cities;
    static List<Edge> edges;

    @FXML
    private Button loadB;

    @FXML
    void BothBA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BothVeiw.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bothB.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadBA(ActionEvent event) throws Exception {
        FileManagementSystem fms = new FileManagementSystem();
        fms.openFileChooser();

        cities = fms.getCities();

        List<Edge> edges = fms.getEdges();

        MainSceneController.edges = edges;

        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i));
        }
    }

}