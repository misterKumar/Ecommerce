package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
        @FXML
        public void initialize(){
                if(!Main.emailID.equals("")){
                        loginbutton.setOpacity(0);
                        loginbutton.setDisable(true);
                        email.setText(Main.emailID);
                }
        }
        @FXML
        Button loginbutton,logoutbutton;
        @FXML
        Label email;
//        @FXML
//        Button searchbutton;

        @FXML
        public void login(MouseEvent e) throws IOException {
                AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginpage.fxml"));
                Main.root.getChildren().add(loginpage);
        }
        @FXML
        TextField searchtext;
        @FXML
        public void Search(MouseEvent e) throws IOException, SQLException {
                ProductPage productPage=new ProductPage();

                Header header=new Header();
                AnchorPane productPane=new AnchorPane();
                productPane.getChildren().add(productPage.productsbySearch(searchtext.getText()));
                productPane.setTranslateX(50.0);
                productPane.setTranslateY(80.0);
                Main.root.getChildren().clear();
                Main.root.getChildren().addAll(header.root,productPane);
        }

        @FXML
        public void logoutappear(MouseEvent e){
                if(logoutbutton.getOpacity()==0){
                        logoutbutton.setOpacity(1);
                }
                else {
                     logoutbutton.setOpacity(0);
                }
        }
        @FXML
        public void logout(MouseEvent e) throws IOException {
                if(logoutbutton.getOpacity()==1) {
                        Main.emailID = "";
                        logoutbutton.setOpacity(0);
                        Header header = new Header();
                        Main.root.getChildren().add(header.root);
                }
        }
}
