package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HeaderController {
        @FXML
        Button loginbutton;

        @FXML
        public void login(MouseEvent e) throws IOException {
                AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginpage.fxml"));
                Main.root.getChildren().add(loginpage);
        }
}
