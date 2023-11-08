package main.manager;
/*  main.manager
 *
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to represent the Manager that will log onto the application.
 * The class is used to load and run the Manager screen that allows a user to use the main system.
 * The Manager class is a super class for the ManagerController class
 */
public class Manager extends Application {

    public static Stage managerStage = new Stage();
    public void managerStage(Stage primaryStage) throws Exception {
        managerStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/main/manager/manager.fxml"));   // Load FXML "Manager"

        managerStage.setTitle("MANAGER PAGE");                                     // Setting stage title
        managerStage.setScene(new Scene(root, 1080, 720));                    // Setting scene and display size
        managerStage.show();
    }

    public static void closeManagerWindow() {
        managerStage.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called managerStage start (Manager.java)");
        managerStage(stage);
    }
}