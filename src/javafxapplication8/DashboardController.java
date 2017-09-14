/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import com.jfoenix.controls.JFXSpinner;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class DashboardController implements Initializable {

 @FXML
    private Label products_num;

    @FXML
    private Label orders_num;

    @FXML
    private Label customers_num;
    
    @FXML
    private JFXSpinner img;
    private static boolean check=false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
               img.setVisible(false);
            }
        });

        new Thread(new Runnable(){
            @Override
            public void run() {
         if(!check)
        {
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                     img.setVisible(true);
                }
            });
             Order or= new Order();
             Products p=new Products();
             Customer c= new Customer();
     try {
        
         or.count();
         p.count();
         c.count();
         p.listProducts();
         or.listOrders();
         c.listCustomer();
          Platform.runLater(new Runnable(){
             @Override
             public void run() { 
              orders_num.setText(Shopify.getOrderNum()+"");
              products_num.setText(Shopify.getProductNum()+"");
              customers_num.setText(Shopify.getCustomerNum()+"");
                 img.setVisible(false);
             }
         });
         check=true;
     } catch (IOException ex) {
         Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
         check=false;
     }
              
        }
         else{
             Platform.runLater(new Runnable(){
             @Override
             public void run() { 
              orders_num.setText(Shopify.getOrderNum()+"");
              products_num.setText(Shopify.getProductNum()+"");
              customers_num.setText(Shopify.getCustomerNum()+"");
                 img.setVisible(false);
             }
         });
         }
            }

        }).start();
      
              
    }    
    
}
