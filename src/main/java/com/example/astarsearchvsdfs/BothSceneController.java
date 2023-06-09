package com.example.astarsearchvsdfs;

import com.example.astarsearchvsdfs.Engine.AStarAlgorithm;
import com.example.astarsearchvsdfs.Engine.DFSAlgorithm;
import com.example.astarsearchvsdfs.model.City;
import com.example.astarsearchvsdfs.model.Edge;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BothSceneController implements Initializable {
    public TextField routeATF;

    Map<String, City> cities;
    List<Edge> edges;
    public ComboBox<City> sourceCom;
    public ComboBox<City> disCom;
    public TextField disATF;
    public TextField timeATF;
    public TextField routeDTF;
    public TextField disDTF;
    public TextField timeDTF;

    public void goBackA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainVeiw.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) routeATF.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startBA(ActionEvent actionEvent) {

        if(sourceCom.getValue() == null || disCom.getValue() == null)
            return;

        AStarAlgorithm aStar = new AStarAlgorithm();
        DFSAlgorithm dfsAlgorithm = new DFSAlgorithm();
        double time1 = System.currentTimeMillis();
        List<City> AStarPath = aStar.aStar(sourceCom.getValue(), disCom.getValue(), edges, cities);
        time1 = System.currentTimeMillis() - time1;
        double time2 = System.currentTimeMillis();
        List<City> dfsPath = dfsAlgorithm.dfs(sourceCom.getValue(), disCom.getValue(), edges);
        time2 = System.currentTimeMillis() - time2;

        timeATF.setText(time1 + "ms");
        timeDTF.setText(time2 + "ms");

        StringBuilder sb = new StringBuilder("");
        double dis = 0.0;
        for (int i = 0; i < AStarPath.size(); i++) {
            if(i < AStarPath.size() - 1) {
                sb.append(AStarPath.get(i)).append(" -> ");
                dis += AStarAlgorithm.getDistance(AStarPath.get(i), AStarPath.get(i+1), edges);
            } else {
                sb.append(AStarPath.get(i));
            }
        }
        disATF.setText(dis + "km");
        routeATF.setText(sb.toString());

        StringBuilder sbDFS = new StringBuilder("");
        double disDFS = 0.0;
        for (int i = 0; i < dfsPath.size(); i++) {
            if(i < dfsPath.size() - 1) {
                sbDFS.append(dfsPath.get(i)).append(" -> ");
                disDFS += AStarAlgorithm.getDistance(dfsPath.get(i), dfsPath.get(i+1), edges);
            } else {
                sb.append(dfsPath.get(i));
            }
        }
        disDTF.setText(disDFS + "km");
        routeDTF.setText(sbDFS.append(dfsPath.get(dfsPath.size() - 1)).toString());

    }

    public void semABA(ActionEvent actionEvent) {

    }

    public void semDBA(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sourceCom.getItems().clear();
        disCom.getItems().clear();
        edges = MainSceneController.edges;
        cities = MainSceneController.cities;

        for (String key : cities.keySet()) {
            City value = cities.get(key);
            sourceCom.getItems().add(value);
            disCom.getItems().add(value);
        }
    }



}
