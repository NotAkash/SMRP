package main.data;/*  main.data
 *
 * Project Name: SMRP
 *
 * Version: 0.01 04-10-2020 21:59 2020
 *
*

 *
 */

import main.InventoryData;
import main.ProductData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class productLog {

    private int key;
    private String code;
    private String name;
    private int amount;
    private int under;
    private int over;

    public productLog(ProductData inv) {
        setKey(inv.getProdKey());
        setCode(inv.getProdCode());
        setName(inv.getProdName());
        setAmount(inv.getProdAmount());
        setUnder(inv.getProdUnder());
        setOver(inv.getProdOver());
    }

    public void setKey(int key) { this.key = key; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setUnder(int under) { this.under = under; }
    public void setOver(int over) { this.over = over; }
    public void logProduct(){
        String line =  "";
        String splitBy = ","; // use comma as separator
        String file = "src/main/data/productLog.csv"; // main.data file URL
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append("\n"+key+","+code +","+name+","+amount+","+under+","+over);
            System.out.println("Appended Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
