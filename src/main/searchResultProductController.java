package main;

import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


import javafx.fxml.FXML;
import main.data.databaseProduct;
import main.inventory.searchResult;

public class searchResultProductController extends searchResult{

    // jagar.me/post/passingdatainjavafx
    @FXML
    public Text queryText;
    @FXML
    public Text filterText;

    @FXML
    public TextField searchPhrase;
    @FXML
    private TableView<ProductData> productTable;
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



    String getPhrase;
    String getChoice;


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
        colUnder.setCellValueFactory(new PropertyValueFactory<>("prodUnder"));
        colOver.setCellValueFactory(new PropertyValueFactory<>("prodOver"));

    }

    public void doSearch(){
        System.out.println("initialized search");
        try{
            System.out.println("Called Init Search");
            //Get InventoryData array from databaseProduct
            if (getChoice.equals("By Sr Number")){
                System.out.println("search Key: "+ Integer.parseInt(getPhrase));
                ObservableList<ProductData> productRecords = databaseProduct.showSearchResult(Integer.parseInt(getPhrase));
                productTable.setItems(productRecords);
                //queryText.setText(Integer.toString(searchFKey));
            } else if(getChoice.equals("By Product Code")){
                System.out.println("search Code: "+ getPhrase);
                ObservableList<ProductData> productRecords = databaseProduct.showSearchResult(getPhrase, 0);
                productTable.setItems(productRecords);
//                queryText.setText(getChoice);
            } else if(getChoice.equals("By Product Name")){
                System.out.println(getPhrase+"name?");
                ObservableList<ProductData> productRecords = databaseProduct.showSearchResult(getPhrase, 1);
                productTable.setItems(productRecords);
//                queryText.setText(getPhrase);
            } else {System.out.println("Search Result Initialize Error");}


        }catch (NullPointerException e){ e.printStackTrace();}
    }
}
