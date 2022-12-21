package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage {
    ListView<HBox> products;


    ListView<HBox> productsbySearch(String searchinput) throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=Main.connection.executeQueries("select * from product");
        while (res.next()) {
            if(res.getString("ProductName").toLowerCase().contains(searchinput.toLowerCase())) {
                Label name = new Label();
                Label productId = new Label();
                Label price = new Label();
                Label quantity = new Label();
                Button buy = new Button();

                name.setMinWidth(100);
                productId.setMinWidth(50);
                price.setMinWidth(50);
                quantity.setMinWidth(50);

                buy.setText("Buy");
                HBox productDetails = new HBox();

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (Main.emailID.equals("")) {
                            Dialog<String> dialog=new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("Please login first");
                            dialog.showAndWait();
                        } else {
                            System.out.println("You are logged in with " + Main.emailID);
                            Order order = new Order();

                            try {
                                order.placeOrder(productId.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("buy button is getting clicked");
                    }
                });

                //these syntax is used to adjust listview dimensions.
                products.setPrefHeight(420);
                products.setPrefWidth(400);

                name.setText(res.getString("ProductName"));
                price.setText(res.getString("price"));
                productId.setText(res.getString("ProductID"));
                quantity.setText(res.getString("AvailableQuantity"));
                productDetails.getChildren().addAll(productId, name, price, quantity, buy);
                productList.add(productDetails);
            }
        }

        products.setItems(productList);
        return products;
    }

    ListView<HBox> products() throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=Main.connection.executeQueries("select * from product");
        while (res.next()) {
            Label name = new Label();
            Label productId = new Label();
            Label price = new Label();
            Label quantity=new Label();
            Button buy = new Button();

            name.setMinWidth(100);
            productId.setMinWidth(50);
            price.setMinWidth(50);
            quantity.setMinWidth(50);

            buy.setText("Buy");
            HBox productDetails = new HBox();

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(Main.emailID.equals("")){
                        Dialog<String> dialog=new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("Please Login first");
                        dialog.showAndWait();
                    }
                    else {
                        System.out.println("You are logged in with "+Main.emailID);
                        Order order=new Order();

                        try {
                            order.placeOrder(productId.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("buy button is getting clicked");
                }
            });

            //these syntax is used to adjust listview dimensions.
            products.setPrefHeight(420);
            products.setPrefWidth(400);

            name.setText(res.getString("ProductName"));
            price.setText(res.getString("price"));
            productId.setText(res.getString("ProductID"));
            quantity.setText(res.getString("AvailableQuantity"));
            productDetails.getChildren().addAll(productId, name, price, quantity, buy);
            productList.add(productDetails);
        }

        products.setItems(productList);
        return products;
    }
}
