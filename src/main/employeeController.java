package main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import main.data.databaseInventory;
import main.inventory.addInventory;
import main.inventory.deleteInventory;
import main.inventory.editInventory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import main.employee.Employee;
import main.inventory.searchInventory;
import main.manager.Manager;
import main.data.Client;

import java.util.Date;


public class employeeController extends Employee {
    @FXML
    private Button stockInventory;

    @FXML
    private Button editStockedInventory;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Tab productTab;

    @FXML
    private TableView<InventoryData> inventTable;
    @FXML
    private TableColumn<InventoryData, Integer> colID;
    @FXML
    private TableColumn<InventoryData, String> colCode;
    @FXML
    private TableColumn<InventoryData, String> colName;
    @FXML
    private TableColumn<InventoryData, Integer> colAmount;
    @FXML
    private TableColumn<InventoryData, String> colLocation;
    @FXML
    private TableColumn<InventoryData, Date> colDate;


    public boolean isAdmin;;
//    ObservableList<InventoryData> ObList = FXCollections.observableArrayList();

    @FXML private void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("prodKey"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("prodCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("prodAmount"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("prodLocation"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("prodDate"));

        // Get InventoryData array from databaseInventory
        ObservableList<InventoryData> inventoryRecords = databaseInventory.showInventory();
        inventTable.setItems(inventoryRecords);
        checkManagerTab();
    }

    public void checkManagerTab(){
        Client client = new Client();
        String checkAdmin = client.getLastAuth();
        System.out.println(isAdmin);
        if(productTab!=null) {
            if (!checkAdmin.toLowerCase().equals("admin")) {
                productTab.setDisable(true);
            } else {
                System.out.println("not false");
                productTab.setDisable(false);
            }
        }

    }
    @FXML
    public void reload(){
        // Get InventoryData array from databaseInventory

        if(inventTable!=null){
            ObservableList<InventoryData> inventoryRecords = databaseInventory.showInventory();
            inventTable.refresh();
            inventTable.setItems(inventoryRecords);
            checkManagerTab();
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

    public void handleAddInvent() {
        addInventory add = new addInventory();
        try {
            add.start(addInventory.addStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML public void openManager() throws Exception {
        Manager m = new Manager();
        Stage stage = (Stage) stockInventory.getScene().getWindow();
        stage.close();
        m.start(Manager.managerStage);

    }

    public void handleEditInventory() {
        System.out.println("Edit Clicked");
        editInventory edit = new editInventory();
        try {
            edit.start(editInventory.editStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleShipInventory() {
        System.out.println("Delete Clicked");
        deleteInventory del = new deleteInventory();
        try {
            del.start(deleteInventory.deleteStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleSearchInventory() {
        System.out.println("Search Clicked");
        searchInventory search = new searchInventory();
        try {
            search.start(searchInventory.searchStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

// xmlns="http://javafx.com/javafx/11.0.1"