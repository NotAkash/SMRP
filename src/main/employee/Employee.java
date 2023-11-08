package main.employee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to represent Employee
 */
public class Employee extends Application {

    public static Stage employeeStage = new Stage();

    public void employeeStage(Stage primaryStage) throws Exception{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/main/employee/employee.fxml"));
        Parent root = loader.load();
        employeeStage.setScene(new Scene(root, 1080,720));// Setting scene and display size
        employeeStage.setTitle("EMPLOYEE PAGE");

        employeeStage.show();

    }

    public static void closeEmployeeWindow(){

        System.out.println("called closeempoy");
        employeeStage.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Called employeeStage start (Employee.java)");
        employeeStage(stage);
    }

}