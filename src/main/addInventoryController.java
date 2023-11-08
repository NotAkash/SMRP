package main;/*  main
 *
 * Project Name: SMRP
 *
 * Version: 0.01 31-08-2020 13:48 2020
 *

 *

 *
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import main.employee.Employee;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.data.databaseInventory;
import main.inventory.addInventory;


import java.sql.Date;

public class addInventoryController{

    @FXML
    TextField addCode;
    @FXML
    TextField addName;
    @FXML
    TextField addAmount;
    @FXML
    TextField addLocation;
    @FXML
    DatePicker addDate;
    @FXML
    ActionEvent event;

    String stockCode;
    String stockName;
    int stockAmount;
    String stockLocation;
    Date stockDate;
    int nextKey;

    public boolean isValid(){
        try {
            stockCode = addCode.getText();
            stockName = addName.getText();
            stockAmount = Integer.parseInt(addAmount.getText());
            stockLocation = addLocation.getText();
            stockDate = Date.valueOf(addDate.getValue());
        }catch (Exception e){
            return false;
        }finally{
            if (!stockCode.equals("") && !(stockAmount==0) && !stockName.equals("") &&
                    !stockLocation.equals("") && !stockDate.toString().isEmpty()) {
                System.out.println("Stock Add IS VALID");
                return true;
            }else {return false;}
        }


    }
    public void stockItem() throws Exception{
        if (isValid()) {
            InventoryData existingRecord = databaseInventory.searchInventory(stockCode);
            nextKey = databaseInventory.getNextKey(stockCode);
            InventoryData inv = new InventoryData(nextKey, stockCode, stockName, stockAmount,
                    stockLocation, stockDate);
            if(existingRecord.getProdKey() == -1){
                System.out.println("ADDING:");
                databaseInventory.addInventory(inv); //Ship inv (data structure) to database
            }else {
                System.out.println("UPDATING");
                inv.setProdKey(existingRecord.getProdKey());
                inv.setprodAmount(existingRecord.getProdAmount()+stockAmount);
                databaseInventory.updateInventory(inv,true);
            }
            //https://www.youtube.com/watch?v=ZuHcl5MmRck
            addInventory.closeAddWindow();
            Employee.closeEmployeeWindow();
            Employee e = new Employee();
            e.start(Employee.employeeStage);

        }else{
            addCode.setText("FILL ALL VALUES");
            addName.setText("FILL ALL VALUES");
            addAmount.setText("FILL ALL VALUES");
            addLocation.setText("FILL ALL VALUES");
        }
    }



}