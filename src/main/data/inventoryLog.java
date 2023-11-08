package main.data;

import main.InventoryData;

import java.io.*;
import java.util.Date;


public class inventoryLog {
    private int key;
    private String code;
    private String name;
    private int amount;
    private String location;
    private Date date;

    public inventoryLog(InventoryData inv) {
        setKey(inv.getProdKey());
        setCode(inv.getProdCode());
        setName(inv.getProdName());
        setAmount(inv.getProdAmount());
        setLocation(inv.getProdLocation());
        setDate(inv.getProdDate());
    }

    public void setKey(int key) { this.key = key; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(Date date) { this.date = date; }

    public void logInventory(){
        String line =  "";
        String splitBy = ","; // use comma as separator
        String file = "src/main/data/inventoryLog.csv"; // main.data file URL
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append("\n"+key+","+code +","+name+","+amount+","+location+","+date);
            System.out.println("Appended Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
