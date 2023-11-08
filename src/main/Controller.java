package main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.data.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import main.employee.Employee;
import main.inventory.searchInventory;

import java.sql.Timestamp;

public class Controller extends Main {
    @FXML                          // Get FXML input to controller
    Scene login;
    @FXML
    private TextField userName;     // Username text field
    @FXML
    private PasswordField passWord;     // Password field
    @FXML
    private MenuItem whDelhi;
    @FXML
    private MenuItem whPunjab;
    @FXML
    private MenuButton whButton;

    private Timestamp time = new Timestamp(System.currentTimeMillis());

//Main Controller
    // As per fxml file,  on-action: access handleLogin function
    public void handleLogin() throws Exception {
        if (!userName.getText().equals("") && !passWord.getText().equals("") && !handleChoice().equals("Warehouse")){
            
            Client client = new main.data.Client(userName.getText(), passWord.getText(), whButton.getText());
            String type = client.validateClient();
            if (type.equals("employee")){
                Employee e = new Employee();
                Main.closeMainWindow();
                e.start(Employee.employeeStage);

            } else if(type.equals("manager") || type.equals("admin")){
                Employee e = new Employee();
                Main.closeMainWindow();
                e.start(Employee.employeeStage);
                System.out.println("InventoryData==true");

            } else {
                System.out.println(userName.getText()+" <- IS USERNAME -> " +type);
                userName.setPromptText("Invalid Credential");
            }
        } else {
            userName.setPromptText("Please try again");
            passWord.setPromptText("Please try again");
        }
        
        
    }
    
    public void handleChoice(ActionEvent event) throws Exception {
        if (event.getSource() == whDelhi) { whButton.setText(whDelhi.getText());
        }else if (event.getSource() == whPunjab) {
            whButton.setText(whPunjab.getText());
        }else { whButton.setText("Warehouse"); }
    }
    
    public String handleChoice() {
        if (!whButton.getText().equals("Warehouse")) {
            return (whButton.getText());
        } else {
            return whButton.getText();
        }
    }


}

