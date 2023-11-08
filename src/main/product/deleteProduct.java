package main.product;
/*  delete
 *
 * Project Name: SMRP
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class deleteProduct extends Application {

    public static Stage deleteStage = new Stage();

    public void deleteProd(Stage primaryStage) throws Exception{
        deleteStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/product/deleteProduct.fxml"));   // Load FXML "Employee"

        deleteStage.setTitle("DELETE PRODUCT");                                     // Setting stage title
        deleteStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        deleteStage.show();
    }

    public static void closeDeleteWindow(){
        deleteStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called deleteProduct start");
        deleteProd(stage);
    }

}
