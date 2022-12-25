package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signuppage {
    @FXML
    TextField email,Name,Type;
    @FXML
    PasswordField Passcode;
    @FXML
    public void submit(MouseEvent e) throws SQLException, IOException {


        String query =String.format("Insert into user values ('%s','%s','%s','%s')",email.getText(),Name.getText(),Passcode.getText(),Type.getText().toLowerCase());
        int response =Main.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Success");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("SignUp successfull");
            dialog.showAndWait();
            dialog.setContentText("you can login now!");
            dialog.showAndWait();

            ProductPage productPage=new ProductPage();
            Header header=new Header();
            AnchorPane productPane=new AnchorPane();
            productPane.getChildren().add(productPage.products());
            productPane.setTranslateX(50.0);
            productPane.setTranslateY(80.0);
            Main.root.getChildren().clear();
            Main.root.getChildren().addAll(header.root,productPane);
        }
    }
}
