package main;

import java.util.Date;

/*  main

 */

public class ProductData{
    private int prodKey;
    private String prodCode;
    private String prodName;
    private int prodAmount;
    private int prodUnder;
    private int prodOver;

    public ProductData(int prodKey, String prodCode, String prodName, int prodAmount, int prodUnder, int prodOver){
        setProdKey(prodKey);
        setProdCode(prodCode);
        setProdName(prodName);
        setprodAmount(prodAmount);
        setProdUnder(prodUnder);
        setProdOver(prodOver);
    }
    public int getProdKey(){return prodKey;}
    public String getProdCode(){return prodCode;}
    public String getProdName(){return prodName;}
    public int getProdAmount(){return prodAmount;}
    public int getProdUnder(){return prodUnder;}
    public int getProdOver(){return prodOver;}

    public void setProdKey(int prodKey){this.prodKey = prodKey;}
    public void setProdCode(String prodCode) {this.prodCode = prodCode;}
    public void setProdName(String prodName) {this.prodName = prodName;}
    public void setprodAmount(int prodAmount) {this.prodAmount = prodAmount;}
    public void setProdUnder(int prodUnder) {this.prodUnder = prodUnder;}
    public void setProdOver(int prodOver) {this.prodOver = prodOver;}
}

