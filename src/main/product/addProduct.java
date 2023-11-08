package main.product;
/*  login.employee
 *
 * Project Name: SMRP
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addProduct extends Application {

    public static Stage addStage = new Stage();

    public void addProductStage(Stage primaryStage) throws Exception{
        addStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/product/addProduct.fxml"));   // Load FXML "Employee"

        addStage.setTitle("STOCK PRODUCT");                                     // Setting stage title
        addStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        addStage.show();
    }

    public static void closeAddWindow(){
        addStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called addProduct start");
        addProductStage(stage);
    }

}
