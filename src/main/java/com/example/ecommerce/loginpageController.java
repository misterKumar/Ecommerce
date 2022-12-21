package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
        String query=String.format("select * from user where emailId='%s' AND passcode='%s' ",email.getText(),password.getText());
        //query ="select * from user where emailId='mister@gmail.com' AND passcode='1234'"
        ResultSet res=Main.connection.executeQueries(query);

        if(res.next()){
            Main.emailID=res.getString("emailId");
            String userType = res.getString("userType");
            if(userType.equals("seller")){
                AnchorPane sellerpage= FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                Main.root.getChildren().add(sellerpage);
            }
            else {
                System.out.println("We are logged in as buyer");
                ProductPage productPage=new ProductPage();

                Header header=new Header();
                AnchorPane productPane=new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setTranslateX(50.0);
                productPane.setTranslateY(80.0);
                Main.root.getChildren().clear();
                Main.root.getChildren().addAll(header.root,productPane);
            }
            System.out.println("The user is exist in the table");
        }
        else {
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("LOGIN");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("login failed,please check username/password and tryagain");
            dialog.showAndWait();
        }
    }
}
