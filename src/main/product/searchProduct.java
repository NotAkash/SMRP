package main.product;
/*  main.product
 *
 * Project Name: SMRP
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class searchProduct extends Application{

    public static Stage searchStage = new Stage();

    public void searchProductStage(Stage primaryStage) throws Exception{
        searchStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/product/searchProduct.fxml"));   // Load FXML "Employee"

        searchStage.setTitle("search STOCKED PRODUCT");                                     // Setting stage title
        searchStage.setScene(new Scene(root, 600,400));                       // Setting scene and display size
        searchStage.show();
    }

    public static void closeSearchWindow(){
        searchStage.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called searchProduct start");
        searchProductStage(stage);
    }

}
