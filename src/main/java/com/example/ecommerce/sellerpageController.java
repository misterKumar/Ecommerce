package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerpageController {
    @FXML
    TextField name,price,sellerid,AvailableQuantity;
    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {

        int productID=1;
        ResultSet response2=Main.connection.executeQueries("select max(productId) as productId from product");
        if(response2.next()){
            productID=response2.getInt("productID")+1;
        }
        String query =String.format("Insert into product values (%s,'%s',%s,'%s',%s)",productID,name.getText(),price.getText(),sellerid.getText(),AvailableQuantity.getText());
        int response =Main.connection.executeUpdate(query);
        if(response>0){
            System.out.println("New product is added");
        }
    }
    @FXML
    public void initialize(){
        if(!Main.emailID.equals("")){
            loginsellbutton.setOpacity(0);
            loginsellbutton.setDisable(true);
            email.setText(Main.emailID);
        }
    }
    @FXML
    Button loginsellbutton,logoutsellbutton;
    @FXML
    Label email;
//        @FXML
//        Button searchbutton;

    @FXML
    public void loginsell(MouseEvent e) throws IOException {
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginpage.fxml"));
        Main.root.getChildren().add(loginpage);
    }
    @FXML
    public void logoutsellappear(MouseEvent e){
        if(logoutsellbutton.getOpacity()==0){
            logoutsellbutton.setOpacity(1);
        }
        else {
            logoutsellbutton.setOpacity(0);
        }
    }
    @FXML
    public void logoutsell(MouseEvent e) throws IOException {
        if(logoutsellbutton.getOpacity()==1) {
            Main.emailID = "";
            logoutsellbutton.setOpacity(0);
            Header header = new Header();
            Main.root.getChildren().clear();
            Main.root.getChildren().add(header.root);
        }
    }
}
