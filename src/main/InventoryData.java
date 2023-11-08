package main;

import java.util.Date;

/**
 * InventoryData class is a blueprint for all the inventory data that is stored in the database
 */
public class InventoryData {

    //Constructor is a special method called when an InventoryData method is initalized. Initailizes InventoryData object
    public InventoryData(int prodKey, String prodCode, String prodName, int prodAmount, String prodLocation, Date prodDate){
        setProdKey(prodKey);
        setProdCode(prodCode);
        setProdName(prodName);
        setprodAmount(prodAmount);
        setProdLocation(prodLocation);
        setProdDate(prodDate);
    }
    private int prodKey;
    private String prodCode;
    private String prodName;
    private int prodAmount;
    private String prodLocation;
    private Date prodDate;

    public int getProdKey(){return prodKey;}
    public String getProdCode(){return prodCode;}
    public String getProdName(){return prodName;}
    public int getProdAmount(){return prodAmount;}
    public String getProdLocation(){return prodLocation;}
    public Date getProdDate(){return prodDate;}

    public void setProdKey(int prodKey){this.prodKey = prodKey;}
    public void setProdCode(String prodCode) {this.prodCode = prodCode;}
    public void setProdName(String prodName) {this.prodName = prodName;}
    public void setprodAmount(int prodAmount) {this.prodAmount = prodAmount;}
    public void setProdLocation(String prodLocation) {this.prodLocation = prodLocation;}
    public void setProdDate(Date prodDate) {this.prodDate = prodDate;}
}

/*
    May 2020 Subject Report:
        1) This session most candidates addressed 2 AOKs. this is good
        2) Unpack title, what it means. explain terms indicating meaning + shows familiarity with terms
        3) Not dictionary meaning. Context of TOK
        4) Don't rewrite title

        6) Implications: evaluation of the title, good essays don't talk about a clear answer to the question.
           Bring in other elements to the answer. Explore more perspective
        7)
 */