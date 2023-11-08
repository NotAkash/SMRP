package main;

import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import main.data.databaseInventory;

import javafx.fxml.FXML;
import main.inventory.searchResult;

import java.util.Date;

public class searchResultController extends searchResult{

// jagar.me/post/passingdatainjavafx
    @FXML
    public Text queryText;
    @FXML
    public Text filterText;

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


    String getPhrase="";
    String getChoice="";

    public void setPhrase(String phrase){
        getPhrase  = phrase;
        System.out.println(phrase);
        if(queryText!=null){queryText.setText(phrase);}
        else { System.out.println("PHRASE NULL");}
    }

    public void setChoice(String choice){
        getChoice = choice;
        System.out.println(choice);
        if(filterText!=null){filterText.setText(choice);}
        else { System.out.println("CHOICE NULL");}

    }


    @FXML
    public void initialize() throws Exception{
        colID.setCellValueFactory(new PropertyValueFactory<>("prodKey"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("prodCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("prodAmount"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("prodLocation"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("prodDate"));

    }

    public void doSearch(){
        System.out.println("initialized search");
        try{
            System.out.println("Called Init Search");
            //Get InventoryData array from databaseInventory
            if (getChoice.equals("By Sr Number")){
                System.out.println("search Key: "+ Integer.parseInt(getPhrase));
                ObservableList<InventoryData> inventoryRecords = databaseInventory.showSearchResult(Integer.parseInt(getPhrase));
                inventTable.setItems(inventoryRecords);
                //queryText.setText(Integer.toString(searchKey));
            } else if(getChoice.equals("By Product Code")){
                System.out.println("search Code: "+ getPhrase);
                ObservableList<InventoryData> inventoryRecords = databaseInventory.showSearchResult(getPhrase, 0);
                inventTable.setItems(inventoryRecords);
//                queryText.setText(getChoice);
            } else if(getChoice.equals("By Product Name")){
                System.out.println(getPhrase+"name?");
                ObservableList<InventoryData> inventoryRecords = databaseInventory.showSearchResult(getPhrase, 1);
                inventTable.setItems(inventoryRecords);
//                queryText.setText(getPhrase);
            } else {System.out.println("Search Result Initialize Error");}


        }catch (NullPointerException e){ e.printStackTrace();}
    }
}
