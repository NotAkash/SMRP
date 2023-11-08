package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    // Method Overriding is used here as an Annotation,
    // used whenever Loading a FXML screen in JavaFX
    @Override
    public void start(Stage primStage) throws Exception{
        primaryStage = primStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/login/login.fxml"));   // Load FXML "Employee"   // Load FXML "login"
        primaryStage.setTitle("Welcome to SMRP");                                     // Setting stage title
        primaryStage.setScene(new Scene(root, 800,400));                       // Setting scene and display size
        primaryStage.show();
    }
    public static void closeMainWindow(){
        primaryStage.close();
    }
    // Launch arguments to initialize JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}

