package main;

/*  searchInventory
 *
 * Project Name: SMRP
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.inventory.searchInventory;
import main.inventory.searchResult;
import java.io.IOException;
import static main.inventory.searchResult.searchResultStage;

public class searchInventoryController {
    @FXML
    public TextField searchPhrase;
    @FXML
    private MenuButton filterButton;
    @FXML
    public MenuItem filterKey;
    @FXML
    public MenuItem filterCode;
    @FXML
    public MenuItem filterName;
    @FXML
    ActionEvent event;

    String getPhrase;
    String getChoice;

    public boolean isValid() throws NumberFormatException, NullPointerException, RuntimeException{
        try {
            getPhrase = searchPhrase.getText();
            getChoice = handleChoice();
        }catch (Exception e){
            System.out.println(e);
            return  false;
        } finally{
            if(!getPhrase.equals("") && !getChoice.equals("Filter To Select From:")){
                System.out.println("Stock search IS VALID");
                return true;
            }
        }
        return false;

    }

    public void searchItem() throws Exception {
        if(isValid()){
            if(handleChoice().equals("By Sr Number")) {
                System.out.println("chice: "+handleChoice()+ " phrse "+ Integer.parseInt(getPhrase));
                try {
//                    search.stsoart(searchResultStage);
                    // Load FXML "searchResult"
                    FXMLLoader loader =  new FXMLLoader(getClass().getResource("/main/inventory/searchResult.fxml"));
                    Parent root = loader.load();
                    System.out.println("phrase:"+getPhrase+" choice:"+getChoice);
                    searchResultController searchResultC = loader.getController();
                    searchResultC.setPhrase(getPhrase);
                    searchResultC.setChoice(getChoice);

                    Stage resultStage = new Stage();
                    resultStage.setScene(new Scene(root, 745,450)); // Setting scene and display size
                    resultStage.setTitle("searchResult PAGE");                                     // Setting stage title
                    searchResultC.doSearch();
                    resultStage.show();
                    searchResultC.doSearch();
                    searchInventory search = new searchInventory();
                    search.closeSearchWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(handleChoice().equals("By Product Code")){
                System.out.println("DAAA? chice: "+handleChoice()+ " phrse "+ getPhrase);
                try {
                    // Load FXML "searchResult"
                    FXMLLoader loader =  new FXMLLoader(getClass().getResource("/main/inventory/searchResult.fxml"));
                    Parent root = loader.load();
                    System.out.println("phrase: "+getPhrase+" choice: "+getChoice);
                    searchResultController searchResultC = loader.getController();
                    searchResultC.setPhrase(getPhrase);
                    searchResultC.setChoice(getChoice);

                    Stage resultStage = new Stage();
                    resultStage.setScene(new Scene(root, 745,450)); // Setting scene and display size
                    resultStage.setTitle("searchResult PAGE");                                     // Setting stage title
                    searchResultC.doSearch();
                    resultStage.show();
                    searchInventory search = new searchInventory();
                    search.closeSearchWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(handleChoice().equals("By Product Name")){
                System.out.println("chice: "+handleChoice()+ " phrse "+ getPhrase);
                try {
                    // Load FXML "searchResult"
                    FXMLLoader loader =  new FXMLLoader(getClass().getResource("/main/inventory/searchResult.fxml"));
                    Parent root = loader.load();
                    System.out.println("phrase: "+getPhrase+" choice: "+getChoice);
                    searchResultController searchResultC = loader.getController();
                    searchResultC.setPhrase(getPhrase);
                    searchResultC.setChoice(getChoice);

                    Stage resultStage = new Stage();
                    resultStage.setScene(new Scene(root, 745,450)); // Setting scene and display size
                    resultStage.setTitle("searchResult PAGE");                                     // Setting stage title
                    searchResultC.doSearch();
                    resultStage.show();
                    searchInventory search = new searchInventory();
                    search.closeSearchWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else{
            searchPhrase.setText("FILL ALL VALUES");
        }

    }


    public void handleChoice(ActionEvent event) throws Exception {
        if (event.getSource() == filterKey) {
            filterButton.setText(filterKey.getText());
        }else if (event.getSource() == filterCode) {
            filterButton.setText(filterCode.getText());
        }else if (event.getSource() == filterName) {
            filterButton.setText(filterName.getText());
        } else {filterButton.setText("Filter To Select From:"); }
    }

    public String handleChoice() {
        return (filterButton.getText());
    }


}
