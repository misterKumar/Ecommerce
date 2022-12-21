package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public  static String emailID;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailID="";
        connection=new DatabaseConnection();
//        ResultSet res=connection.executeQueries("select * from temp");
//        while (res.next()){
//            int number=res.getInt("tempnum");
//            System.out.println(number);
//        }
//
//        int rows=connection.executeUpdate("Insert into temp values(10)");
//        System.out.println("numbers of affected is ="+rows);



        root=new Group();
        Header header=new Header();
        ProductPage productPage=new ProductPage();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.products());
        root.getChildren().addAll(header.root,productPane);
        productPane.setTranslateX(50.0);
        productPane.setTranslateY(80.0);
        stage.setTitle("E-Commerce");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
        //create a lamda function.
        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("connection is closed succesfully");
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}