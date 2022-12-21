package com.example.ecommerce;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
     void placeOrder(String productID) throws SQLException {
        ResultSet res=Main.connection.executeQueries("Select max(orderID) as orderID from orders");
        int OrderID=1;
        if(res.next()){
            OrderID=res.getInt("orderID")+1;
        }
//        Date date=new Date(Calendar.getInstance().getTime().getTime());//this will only add date.
         Timestamp ts=new Timestamp(Calendar.getInstance().getTime().getTime());//this will add both date and time.
//         System.out.println(date.toString()+" "+ts.toString());
        String query=String.format("Insert Into orders values(%s,%s,'%s','%s')",OrderID,productID,Main.emailID,ts);
        int response=Main.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your order is placed");
            dialog.showAndWait();
        }
        else{
            System.out.println("The order is not placed");
        }
    }
}
