/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * */
 class OrderList extends RecursiveTreeObject<OrderList>
     {
     
     /*
       String id =ob.getLong("id")+"";
           String total_price=ob.getDouble("total_price")+"";
           String subtotal_price=ob.getDouble("subtotal_price")+"";
           String total_tax=ob.getDouble("total_tax")+"";
           String currency=ob.getString("currency");
           String order_number=ob.getInt("order_number")+"";
     
     
     
     */
            StringProperty serial;
            StringProperty id;
            StringProperty total_price;
            StringProperty subtotal_price;
            StringProperty total_tax;
            StringProperty currency;
            StringProperty order_number;
    
        public OrderList(
                String serial,
                String id,
                String total_price,
                String subtotal_price,
                String total_tax,
                String currency,
                String order_number
        )
        {
          this.serial=new SimpleStringProperty(serial); 
          this.id=new SimpleStringProperty(id); 
          this.total_price=new SimpleStringProperty(total_price); 
          this.subtotal_price=new SimpleStringProperty(subtotal_price); 
          this.total_tax=new SimpleStringProperty(total_tax); 
          this.currency=new SimpleStringProperty(currency); 
          this.order_number=new SimpleStringProperty(order_number); 
         
        }
  
     }
