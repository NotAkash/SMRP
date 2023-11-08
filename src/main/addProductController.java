package main;
/*  main
 *
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.data.databaseInventory;
import main.employee.Employee;
import javafx.scene.control.TextField;
import main.data.databaseProduct;
import main.manager.Manager;
import main.product.addProduct;


import java.sql.Date;

public class addProductController{

    @FXML
    TextField addCode;
    @FXML
    TextField addName;
    @FXML
    TextField addUnder;
    @FXML
    TextField addOver;

    @FXML
    ActionEvent event;

    int nextKey;
    String stockName;
    String stockCode;
    int stockAmount;
    int stockUnder;
    int stockOver;

    public boolean isValid(){

        try {
            stockCode = addCode.getText();
            stockName = addName.getText();
            stockUnder = Integer.parseInt(addUnder.getText());
            stockOver = Integer.parseInt(addOver.getText());
        }catch (java.lang.NumberFormatException e){
            return false;
        }finally{
            if (!stockCode.equals("") && !stockName.equals("") &&
                    !(stockUnder==0) && !(stockOver==0)) {
                System.out.println("Stock Add IS VALID");
                return true;
            }
            return false;
        }

    }
    public void stockItem() throws Exception{
        System.out.println("something works");
        if (isValid()) {
            ProductData existingRecord = databaseProduct.searchProduct(stockCode);
            nextKey = databaseProduct.getNextKey(stockCode);
            stockAmount = databaseInventory.searchInventory(stockCode).getProdAmount();
            ProductData inv = new ProductData(nextKey, stockCode, stockName, stockAmount,
                    stockUnder, stockOver);
            if(existingRecord.getProdKey() == -1){
                System.out.println("ADDING:");
                databaseProduct.addProduct(inv); //Ship inv (data structure) to database
            }else {
                addCode.setPromptText("PRODUCT EXISTS");
            }
            //https://www.youtube.com/watch?v=ZuHcl5MmRck
            addProduct.closeAddWindow();
            Manager.closeManagerWindow();
            Manager m = new Manager();
            m.start(Manager.managerStage);

        }else{
            addCode.setText("FILL ALL VALUES");
            addName.setText("FILL ALL VALUES");
            addOver.setText("FILL ALL VALUES");
            addUnder.setText("FILL ALL VALUES");
        }
    }



}