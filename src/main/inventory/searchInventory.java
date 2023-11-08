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

public class searchInventory extends Application {

    public static Stage searchStage = new Stage();

    public void searchInventStage(Stage primaryStage) throws Exception{
        searchStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/inventory/searchInventory.fxml"));   // Load FXML "Employee"

        searchStage.setTitle("search STOCKED INVENTORY");                                     // Setting stage title
        searchStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        searchStage.show();
    }

    public static void closeSearchWindow(){
        searchStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called searchInventory start");
        searchInventStage(stage);
    }

}
