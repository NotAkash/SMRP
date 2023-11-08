package main.inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addInventory extends Application {

    public static Stage addStage = new Stage();

    public void addInventStage(Stage primaryStage) throws Exception{
        addStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/inventory/addInventory.fxml"));   // Load FXML "Employee"

        addStage.setTitle("STOCK INVENTORY");                                     // Setting stage title
        addStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        addStage.show();
    }

    public static void closeAddWindow(){
        addStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called addInventory start");
        addInventStage(stage);
    }

}
