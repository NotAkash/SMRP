package main.product;
/*  main.inventory
 *
 * Project Name: SMRP
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class editProduct extends Application {

    public static Stage editStage = new Stage();

    public void editInventStage(Stage primaryStage) throws Exception{
        editStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/product/editProduct.fxml"));   // Load FXML "Employee"

        editStage.setTitle("EDIT PRODUCT");                                     // Setting stage title
        editStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        editStage.show();
    }

    public static void closeEditWindow(){
        editStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called editProduct start");
        editInventStage(stage);
    }

}
