package main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import main.data.databaseProduct;
import main.inventory.addInventory;
import main.inventory.deleteInventory;
import main.inventory.editInventory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import main.employee.Employee;
import main.manager.Manager;
import main.product.addProduct;
import main.product.deleteProduct;
import main.product.editProduct;
import main.product.searchProduct;


/**
 *  This class is a subclass for the Manager and is a Controller for the FXML elements.
 *  This class is used to handle client input and displays data to the client.
 *  Inheritance is used to get methods and fields of the Manage class
 */
public class managerController extends Manager {
    @FXML
    private Button stockInventory;
    @FXML
    private Button editStockedInventory;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Tab ProductTab;
    @FXML
    private TableView<ProductData> inventTable;
    @FXML
    private TableColumn<ProductData, Integer> colID;
    @FXML
    private TableColumn<ProductData, String> colCode;
    @FXML
    private TableColumn<ProductData, String> colName;
    @FXML
    private TableColumn<ProductData, Integer> colAmount;
    @FXML
    private TableColumn<ProductData, Integer> colUnder;
    @FXML
    private TableColumn<ProductData, Integer> colOver;
    @FXML
    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("prodKey"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("prodCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("prodAmount"));
        colUnder.setCellValueFactory(new PropertyValueFactory<>("prodUnder"));
        colOver.setCellValueFactory(new PropertyValueFactory<>("prodOver"));

        // Get InventoryData array from databaseInventory
        ObservableList<ProductData> inventoryRecords = databaseProduct.showProduct();
        inventTable.setItems(inventoryRecords);

    }

    @FXML
    public void reload(){
        // Get InventoryData array from databaseInventory

        if(inventTable!=null){
            ObservableList<ProductData> inventoryRecords = databaseProduct.showProduct();
            inventTable.refresh();
            inventTable.setItems(inventoryRecords);

        }else { System.out.println("null??");}

    }


    @FXML
    public void closeAction(ActionEvent e){
        if(e!=null){
            Stage stage = (Stage) e.getSource();
            stage.close();
            reload();
        }else {System.out.println("null?? at closeAction(e)");}
    }

    public void handleAddProd() {
        addProduct add = new addProduct();
        try {
            add.start(addProduct.addStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openEmployee() throws Exception {
        Employee e = new Employee();
        System.out.println("openEmployee hit");
        Manager.closeManagerWindow();
        e.start(Employee.employeeStage);

    }

    public void handleEditProd() {
        editProduct edit = new editProduct();
        try {
            edit.start(editProduct.editStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteProd() {
        System.out.println("Delete Clicked");
        deleteProduct del = new deleteProduct();
        try {
            del.start(deleteInventory.deleteStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleSearchProduct() {
        System.out.println("Search Clicked");
            searchProduct search = new searchProduct();
        try {
            search.start(searchProduct.searchStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

// xmlns="http://javafx.com/javafx/11.0.1"