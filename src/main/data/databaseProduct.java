package main.data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.ProductData;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


public class databaseProduct {
    private static String host ="jdbc:mysql://localhost:3306/SMRP";
    private static String uName = "root";
    private static String uPass = "Akash@0412";;
    public static void addProduct(ProductData inv) {
        /*
            https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
            https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
            https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
            tutorialspoint.com/jdbc/jdbc-insert-records.html
         */
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "INSERT INTO product VALUES (" + inv.getProdKey() + ", '" + inv.getProdCode() + "', '"
                    + inv.getProdName() + "', " + inv.getProdAmount() + ", '" + inv.getProdUnder() + "', '" + inv.getProdOver()+"')";

            stat.executeUpdate(sql);
            productLog logger = new productLog(inv);
            logger.logProduct();

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

    public static void deleteProduct(String prodCode){
        Connection con = null;
        Statement stat = null;
        try{
            con = DriverManager.getConnection(host,uName,uPass);
            stat = con.createStatement();
            String sql;

            sql = "DELETE FROM `product` WHERE (`KEY` = '" + searchProduct(prodCode).getProdKey() + "');";

            stat.executeUpdate(sql);


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

    public static void updateProduct(ProductData inv){
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql;
            if(inv.getProdAmount()==0) {
                sql = "DELETE FROM `product` WHERE (`KEY` = '" + inv.getProdKey() + "');";
            }else {
                sql = "UPDATE `product` SET `productName` = '" + inv.getProdName() + "', `productAmount` = '" +
                        inv.getProdAmount() + "', `productCode` = '" + inv.getProdCode() + "', `productUnder` = '" +
                        inv.getProdUnder() + "', `productOver` = '" + inv.getProdOver() + "' " +
                        "WHERE (`KEY` = '" + inv.getProdKey() + "');";
            }
            stat.executeUpdate(sql);
            productLog logger = new productLog(inv);
            logger.logProduct();

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

    public static ProductData searchProduct(String prodCode){
        /**
         * Linear search is used to search for a product from the database.
         * Linear search is easy to implement, and very practical as there won’t be too many elements.
         * In this case the search results from the database may be unordered, it is best to use Linear search.
         */
        Connection con = null;
        Statement stat = null;
        ProductData inv = new ProductData(-1,"","",0,-1, -1);
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "select * from product";
            ResultSet resultSet= stat.executeQuery(sql);
            while(resultSet.next()){
                if(resultSet.getString("productCode").equals(prodCode)){
                    inv.setProdKey(resultSet.getInt("KEY"));
                    inv.setProdCode(resultSet.getString("productCode"));
                    inv.setProdName(resultSet.getString("productName"));
                    inv.setprodAmount(resultSet.getInt("productAmount"));
                    inv.setProdUnder(resultSet.getInt("productUnder"));
                    inv.setProdOver(resultSet.getInt("productOver"));
                    break;
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
        return inv;

    }

    public static ObservableList<ProductData> showProduct(){
        //https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
        //https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
        //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        //tutorialspoint.com/jdbc/jdbc-insert-records.html
        ObservableList<ProductData> oblist = FXCollections.observableArrayList();
        oblist.clear();
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "select * from product";
            ResultSet resultSet= stat.executeQuery(sql);

            while(resultSet.next()){
                oblist.add(new ProductData(resultSet.getInt("KEY"), resultSet.getString("productCode"),
                        resultSet.getString("productName"), resultSet.getInt("productAmount"),
                        resultSet.getInt("productUnder"), resultSet.getInt("productOver")));
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

    public static ObservableList<ProductData> showSearchResult(int searchKey){
        //https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
        //https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
        //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        //tutorialspoint.com/jdbc/jdbc-insert-records.html

        ObservableList<ProductData> oblist = FXCollections.observableArrayList();
        oblist.clear();
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql = "select * from product";
            ResultSet resultSet= stat.executeQuery(sql);

            while(resultSet.next()){
                if (resultSet.getInt("KEY")==searchKey){
                    oblist.add(new ProductData(resultSet.getInt("KEY"), resultSet.getString("productCode"),
                            resultSet.getString("productName"), resultSet.getInt("productAmount"),
                            resultSet.getInt("productUnder"), resultSet.getInt("productOver")));
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

    public static ObservableList<ProductData> showSearchResult(String searchTerm, int identifier){
        //https://www.youtube.com/watch?v=LoiQVoNil9Q -- show database video
        //https://www.youtube.com/watch?v=AshoqjeaPkc -- add to db video
        //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        //tutorialspoint.com/jdbc/jdbc-insert-records.html

        ObservableList<ProductData> oblist = FXCollections.observableArrayList();
        oblist.clear();
        Connection con = null;
        Statement stat = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            stat = con.createStatement();
            String sql; ResultSet resultSet;
            if(identifier==0){
                sql = "select * from product WHERE productCode='"+searchTerm+"';";
               resultSet= stat.executeQuery(sql);
            } else{
                sql = "select * from product WHERE productName='"+searchTerm+"';";
               resultSet= stat.executeQuery(sql);
            }
            while(resultSet.next()){
                oblist.add(new ProductData(resultSet.getInt("KEY"), resultSet.getString("productCode"),
                        resultSet.getString("productName"), resultSet.getInt("productAmount"),
                        resultSet.getInt("productUnder"), resultSet.getInt("productOver")));
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



    public static int getNextKey(String prodCode){
        ProductData recordedResult = searchProduct(prodCode);
        int lastKey = recordedResult.getProdKey();
        if(lastKey != -1){ return lastKey; }
        else{
            try {
                Connection con = DriverManager.getConnection(host, uName, uPass);
                Statement stat = con.createStatement();
                String sql = "select * from product";
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
