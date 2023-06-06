module com.example.astarsearchvsdfs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.astarsearchvsdfs to javafx.fxml;
    exports com.example.astarsearchvsdfs;
}