package main;/*  main
 *
 * Project Name: SMRP
 *
 * Version: 0.01 04-10-2020 23:27 2020
 *
*

 *
 */

import main.data.databaseProduct;
import main.manager.Manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.product.deleteProduct;



public class deleteProductController extends deleteProduct {
    @FXML
    TextField delCode;

    @FXML
    ActionEvent event;

    String deleteCode;

    public boolean isValid() throws NumberFormatException, NullPointerException, RuntimeException{
        try {
            deleteCode = delCode.getText();
        }catch (Exception e){
            System.out.println(e);
            return  false;
        } finally{
            if (!deleteCode.equals("")) {
                System.out.println("DELETE IS VALID");
                return true;
            }
        }
        return false;

    }
    public void deleteProd() throws Exception {
        if(isValid()){
            ProductData recordedResult =  databaseProduct.searchProduct(deleteCode);
            if(recordedResult.getProdKey()!=-1){
                databaseProduct.deleteProduct(deleteCode);
                //https://www.youtube.com/watch?v=ZuHcl5MmRck
                closeDeleteWindow();
                Manager.closeManagerWindow();
                Manager m = new Manager();
                m.start(Manager.managerStage);
            }else {
                delCode.setText("ship existing records only");
            }
        }else{
            delCode.setText("FILL ALL VALUES");
        }


    }

}

