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
 * @author Ass hole
 */
 class User extends RecursiveTreeObject<User>
     {
            StringProperty serial;
            StringProperty id;
            StringProperty title;
            StringProperty tags;
            StringProperty vendor;
            StringProperty scope;
            StringProperty createdAt;
            StringProperty price;
            StringProperty product_type;
            StringProperty imageSrc;
            StringProperty htmlBody;
        public User(
                String serial,
                String id,
                String title,
                String tags,
                String vendor,
                String createdAt,
                String price,
                String product_type,
                String imageSrc,
                String htmlBody,
                String Scope
        )
        {
          this.serial=new SimpleStringProperty(serial); 
          this.id=new SimpleStringProperty(id); 
          this.title=new SimpleStringProperty(title); 
          this.tags=new SimpleStringProperty(tags); 
          this.vendor=new SimpleStringProperty(vendor); 
          this.createdAt=new SimpleStringProperty(createdAt); 
          this.price=new SimpleStringProperty(price); 
          this.product_type=new SimpleStringProperty(product_type); 
          this.imageSrc=new SimpleStringProperty(imageSrc); 
          this.htmlBody=new SimpleStringProperty(htmlBody);
           this.scope=new SimpleStringProperty(Scope);
        }
        public String getSerial(){
            return serial.toString();
        }
         public String getprice(){
            return price.toString();
        }
     }
