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
 class customerList extends RecursiveTreeObject<customerList>
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
            StringProperty email;
            StringProperty fname;
            StringProperty lname;
            StringProperty created_AT;
        
    
        public customerList(
                String serial,
                String id,
                String email,
                String fname,
                String lname,
                String created_AT
               
        )
        {
          this.serial=new SimpleStringProperty(serial); 
          this.id=new SimpleStringProperty(id); 
          this.email=new SimpleStringProperty(email); 
          this.fname=new SimpleStringProperty(fname); 
          this.lname=new SimpleStringProperty(lname); 
          this.created_AT=new SimpleStringProperty(created_AT); 
         
         
        }
  
     }
