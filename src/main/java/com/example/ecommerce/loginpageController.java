package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginpageController {

    @FXML
    TextField  email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query=String.format("select * from user where emailId='%s' AND passcode='%s'",email.getText(),password.getText());
        //query ="select * from user where emailId='mister@gmail.com' AND passcode='1234'"
        ResultSet res=Main.connection.executeQueries(query);

        if(res.next()){
            String userType = res.getString("userType");
            if(userType.equals("seller")){
                AnchorPane sellerpage= FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                Main.root.getChildren().add(sellerpage);
            }
            else{

            }
            System.out.println("The user is exist in the table");
        }
        else {
            System.out.println("The user is doesn't exist in the table");
        }
    }
}
