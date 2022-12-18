package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerpageController {
    @FXML
    TextField name,price,sellerid;
    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {

        int productID=1;
        ResultSet response2=Main.connection.executeQueries("select max(productId) as productId from product");
        if(response2.next()){
            productID=response2.getInt("productID")+1;
        }
        String query =String.format("Insert into product values (%s,'%s',%s,'%s')",productID,name.getText(),price.getText(),sellerid.getText());
        int response =Main.connection.executeUpdate(query);
        if(response>0){
            System.out.println("New product is added");
        }
    }
}
