package main;

/*  editInventory
 *
 * Project Name: SMRP
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.data.databaseInventory;
import main.employee.Employee;
import main.inventory.editInventory;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.Date;

public class editInventoryController {
    @FXML
    TextField originalCode;
    @FXML
    TextField updateCode;
    @FXML
    TextField updateName;
    @FXML
    TextField updateLocation;
    @FXML
    DatePicker updateDate;
    @FXML
    ActionEvent event;

    String currentCode;
    String newCode;
    String newName;
    String newLocation;
    Date newDate;

    public boolean isValid() throws NumberFormatException, NullPointerException, RuntimeException{
        try {
            currentCode = originalCode.getText();
            newCode = updateCode.getText();
            newName = updateName.getText();
            newLocation = updateLocation.getText();
            newDate = java.sql.Date.valueOf(updateDate.getValue());
        }catch (Exception e){
            System.out.println(e);
            return  false;
        } finally{
            if (!currentCode.equals("") && !newCode.equals("") && !newName.equals("") &&
                    !newLocation.equals("") && !newDate.toString().isEmpty()) {
                System.out.println("Stock EDIT IS VALID");
                return true;
            }
        }
        return false;

    }
    public void updateItem() throws Exception {
        if(isValid()){
            InventoryData recordedResult =  databaseInventory.searchInventory(currentCode);
            int editKey = recordedResult.getProdKey();
            int editAmount = recordedResult.getProdAmount();
            if(editKey!=-1){
                InventoryData inv = new InventoryData(editKey, newCode,newName,editAmount,newLocation, newDate);
                databaseInventory.updateInventory(inv, true);
                //https://www.youtube.com/watch?v=ZuHcl5MmRck
                editInventory.closeEditWindow();
                Employee.closeEmployeeWindow();
                Employee e = new Employee();
                e.start(Employee.employeeStage);
            }else {
                originalCode.setText("Edit existing records only");
            }
        }else{
            originalCode.setText("FILL ALL VALUES");
        }


    }

}
