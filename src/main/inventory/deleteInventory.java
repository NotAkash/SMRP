package main.inventory;
/*  main.inventory
 *
 * Project Name: SMRP
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class deleteInventory extends Application {

    public static Stage deleteStage = new Stage();

    public void deleteInventStage(Stage primaryStage) throws Exception{
        deleteStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/inventory/deleteInventory.fxml"));   // Load FXML "Employee"

        deleteStage.setTitle("delete STOCKED INVENTORY");                                     // Setting stage title
        deleteStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        deleteStage.show();
    }

    public static void closedeleteWindow(){
        deleteStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called deleteInventory start");
        deleteInventStage(stage);
    }

}
