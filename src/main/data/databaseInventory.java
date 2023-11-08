package main.data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.InventoryData;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

public class databaseInventory {
    private static String host ="jdbc:mysql://localhost:3306/SMRP";
    private static String uName = "root";
    private static String uPass = "Akash@0412";

    public static void addInventory(InventoryData inv) {
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "INSERT INTO inventory VALUES (" + inv.getProdKey() + ", '" + inv.getProdCode() + "', '"
                    + inv.getProdName() + "', " + inv.getProdAmount() + ", '" + inv.getProdLocation() + "', DATE '" + inv.getProdDate()+"')";

            stat.executeUpdate(sql);
            inventoryLog logger = new inventoryLog(inv);
            logger.logInventory();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                if (stat != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    public static void updateInventory(InventoryData inv, boolean stockOrShip){
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql;
            if(inv.getProdAmount()==0) {
                sql = "DELETE FROM `inventory` WHERE (`KEY` = '" + inv.getProdKey() + "');";
            }else {
                if(stockOrShip){
                    sql = "UPDATE `inventory` SET `productName` = '" + inv.getProdName() + "', `productAmount` = '" +
                            inv.getProdAmount() + "', `productCode` = '" + inv.getProdCode() + "', `productLocation` = '" +
                            inv.getProdLocation() + "', `productDate` = '" + inv.getProdDate() + "' " +
                            "WHERE (`KEY` = '" + inv.getProdKey() + "');";
                }else {
                    sql = "UPDATE `inventory` SET `productName` = '" + inv.getProdName() + "', `productAmount` = '" +
                            inv.getProdAmount() + "', `productCode` = '" + inv.getProdCode() +
                            "', `productDate` = '" + inv.getProdDate() + "' " +
                            "WHERE (`KEY` = '" + inv.getProdKey() + "');";
                }
            }

            stat.executeUpdate(sql);
            inventoryLog logger = new inventoryLog(inv);
            logger.logInventory();


        } catch (SQLException throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                if (stat != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static InventoryData searchInventory(String prodCode){
        // show inventory
        Connection con = null;
        Statement stat = null;
        InventoryData inv = new InventoryData(
                -1,"","",0,"", new Date());
        try {
            // Attempts to Select all items and search for
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "select * from inventory";
            ResultSet resultSet= stat.executeQuery(sql);
            while(resultSet.next()){
                if(resultSet.getString("productCode").equals(prodCode)){


                    inv.setProdKey(resultSet.getInt("KEY"));
                    inv.setProdCode(resultSet.getString("productCode"));
                    inv.setProdName(resultSet.getString("productName"));
                    inv.setprodAmount(resultSet.getInt("productAmount"));
                    inv.setProdLocation(resultSet.getString("productLocation"));
                    inv.setProdDate(resultSet.getDate("productDate"));

                    break;
                }
            }

        } catch (SQLException throwables) {
            // catch SQLException and print stack tract
            throwables.printStackTrace();
        } finally {
            try {
                if (stat != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                // close connection
                if (con != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return inv;

    }

    public static ObservableList<InventoryData> showInventory(){
        //https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
        //https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
        //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        //tutorialspoint.com/jdbc/jdbc-insert-records.html
        ObservableList<InventoryData> oblist = FXCollections.observableArrayList();
        oblist.clear();
        Connection con = null; Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "select * from inventory";
            ResultSet resultSet = stat.executeQuery(sql);
            while(resultSet.next()){
                oblist.add(new InventoryData(resultSet.getInt("KEY"), resultSet.getString("productCode"),
                        resultSet.getString("productName"), resultSet.getInt("productAmount"),
                        resultSet.getString("productLocation"), resultSet.getDate("productDate")));
            }
            return oblist;
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                if (stat != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return oblist;
    }

    // showSearchResult if the input is an int
    public static ObservableList<InventoryData> showSearchResult(int searchKey){
        ObservableList<InventoryData> oblist = FXCollections.observableArrayList();
        oblist.clear();
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "select * from inventory";
            ResultSet resultSet= stat.executeQuery(sql);

            while(resultSet.next()){
                if (resultSet.getInt("KEY")==searchKey){
                    oblist.add(new InventoryData(resultSet.getInt("KEY"), resultSet.getString("productCode"),
                            resultSet.getString("productName"), resultSet.getInt("productAmount"),
                            resultSet.getString("productLocation"), resultSet.getDate("productDate")));
                }
            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                if (stat != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return oblist;
    }

    // showSearchResult if the input is a String,
    // differentiate between the input being a productCode and productName
    public static ObservableList<InventoryData> showSearchResult(String searchTerm, int identifier){

        ObservableList<InventoryData> oblist = FXCollections.observableArrayList();
        oblist.clear();
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql; ResultSet resultSet;
            if(identifier==0){
                sql = "select * from inventory WHERE productCode='"+searchTerm+"';";
                resultSet = stat.executeQuery(sql);
            } else{
                sql = "select * from inventory WHERE productName='"+searchTerm+"';";
                resultSet= stat.executeQuery(sql);
            }
            while(resultSet.next()){
                oblist.add(new InventoryData(resultSet.getInt("KEY"), resultSet.getString("productCode"),
                    resultSet.getString("productName"), resultSet.getInt("productAmount"),
                    resultSet.getString("productLocation"), resultSet.getDate("productDate")));
            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                if (stat != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return oblist;

        //https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
        //https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
        //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        //tutorialspoint.com/jdbc/jdbc-insert-records.html

        //https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
        //https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
        //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        //tutorialspoint.com/jdbc/jdbc-insert-records.html
    }



    public static int getNextKey(String prodCode){
        InventoryData recordedResult = searchInventory(prodCode);
        int lastKey = recordedResult.getProdKey();
        if(lastKey != -1){ return lastKey; }
        else{
            try {
                Connection con = DriverManager.getConnection(host, uName, uPass);
                Statement stat = con.createStatement();
                String sql = "select * from inventory";
                ResultSet resultSet= stat.executeQuery(sql);

                while (resultSet.next()) {
                    lastKey = resultSet.getInt("KEY");
                }

                return (lastKey + 1);

            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }
            return lastKey;
        }
    }

}
