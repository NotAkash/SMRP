package main;

/* deleteInventory
 *

 *
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.data.databaseInventory;
import main.employee.Employee;
import main.inventory.deleteInventory;

import java.util.Date;

public class deleteInventoryController {
    @FXML
    TextField delCode;
    @FXML
    TextField delAmount;
    @FXML
    TextField delName;
    @FXML
    TextField delLocation;
    @FXML
    DatePicker delDate;
    @FXML
    ActionEvent event;

    String shipCode;
    String shipName;
    int shipAmount;
    String shipLocation;
    Date shipDate;

    public boolean isValid() throws NumberFormatException, NullPointerException, RuntimeException{
        try {
            shipCode = delCode.getText();
            shipName = delName.getText();
            shipAmount = Integer.parseInt(delAmount.getText());
            shipLocation = delLocation.getText();
            shipDate = java.sql.Date.valueOf(delDate.getValue());
        }catch (Exception e){
            System.out.println(e);
            return  false;
        } finally{
            if (!shipCode.equals("") && !(shipAmount==0) && !shipName.equals("") &&
                    !shipLocation.equals("") && !shipDate.toString().isEmpty()) {
                System.out.println("Stock SHIP IS VALID");
                return true;
            }
        }
        return false;

    }
    public void shipItem() throws Exception {
        if(isValid()){
            InventoryData recordedResult =  databaseInventory.searchInventory(shipCode);
            int shipKey = recordedResult.getProdKey();
            shipAmount = recordedResult.getProdAmount()-shipAmount;
            if(shipKey!=-1){
                InventoryData inv = new InventoryData(shipKey, shipCode,shipName,shipAmount,shipLocation, shipDate);
                databaseInventory.updateInventory(inv,false);
                //https://www.youtube.com/watch?v=ZuHcl5MmRck
                deleteInventory.closedeleteWindow();
                Employee.closeEmployeeWindow();
                Employee e = new Employee();
                e.start(Employee.employeeStage);
            }else {
                delCode.setText("ship existing records only");
            }
        }else{
            delCode.setText("FILL ALL VALUES");
            delName.setText("FILL ALL VALUES");
            delAmount.setText("FILL ALL VALUES");
            delLocation.setText("FILL ALL VALUES");
        }


    }

}
