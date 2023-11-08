package main;

/*  editProduct
 *
 * Project Name: SMRP
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.data.databaseProduct;
import main.employee.Employee;
import main.manager.Manager;
import main.product.editProduct;



public class editProductController extends editProduct{
    @FXML
    TextField editName;
    @FXML
    TextField editCode;
    @FXML
    TextField editUnder;
    @FXML
    TextField editOver;
    @FXML
    ActionEvent event;

    String newName;
    String currentCode;
    int newUnder;
    int newOver;
    public boolean isValid() throws NumberFormatException, NullPointerException, RuntimeException{
        try {
            currentCode = editCode.getText();
            newName = editName.getText();
            newUnder = Integer.parseInt(editUnder.getText());
            newOver = Integer.parseInt(editOver.getText());

        }catch (Exception e){
            System.out.println(e +"caused," + editCode.getText()+" "+editName.getText()+" "+editUnder.getText()+" "+editOver.getText());
            return  false;
        } finally{
            if (!currentCode.equals("") && !newName.equals("") &&
                    !(newUnder==0) && !(newOver==0)) {
                System.out.println("code: "+currentCode+" name:"+newName+" under:"+newUnder+" over:"+newOver);
                System.out.println("Stock EDIT IS VALID");
                return true;
            }
        }
        System.out.println("code: "+currentCode+" name:"+newName+" under:"+newUnder+" over:"+newOver);
        return false;

    }
    public void updateProd() throws Exception {
        if(isValid()){
            ProductData recordedResult =  databaseProduct.searchProduct(currentCode);
            int editKey = recordedResult.getProdKey();
            int editAmount = recordedResult.getProdAmount();
            if(editKey!=-1){
                ProductData inv = new ProductData(editKey, currentCode,newName, editAmount,newUnder, newOver);
                databaseProduct.updateProduct(inv);
                //https://www.youtube.com/watch?v=ZuHcl5MmRck
                editProduct.closeEditWindow();
                Manager.closeManagerWindow();
                Manager m = new Manager();
                m.start(Manager.managerStage);
            }else {
                editCode.setText("Edit existing records only");
            }
        }else{
            editCode.setText("FILL ALL VALUES");
        }


    }

}
