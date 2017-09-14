/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private AnchorPane pane;
      @FXML
    void articles(ActionEvent event) {

    }

    @FXML
    void customer(ActionEvent event) {
              AnchorPane fxm;
         try {
              pane.getChildren().clear();
             fxm = FXMLLoader.load(getClass().getResource("customerView.fxml"));
             pane.getChildren().setAll(fxm);
         } catch (IOException ex) {
             Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @FXML
    void order(ActionEvent event) {
            
          AnchorPane fxm;
         try {
              pane.getChildren().clear();
             fxm = FXMLLoader.load(getClass().getResource("orderView.fxml"));
             pane.getChildren().setAll(fxm);
         } catch (IOException ex) {
             Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
     @FXML
    void products(ActionEvent event) throws IOException {
         pane.getChildren().clear();
          AnchorPane fxm=FXMLLoader.load(getClass().getResource("products.fxml"));
         pane.getChildren().setAll(fxm);
    }
     @FXML
    void dash(ActionEvent event) throws IOException {
            dashboard();
    }
     public void dashboard() throws IOException
     {
         
         pane.getChildren().clear();
          AnchorPane fxm=FXMLLoader.load(getClass().getResource("dashboard.fxml"));
         pane.getChildren().setAll(fxm);
        // pane2 = FXMLLoader.load("dashboard.fxml");
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             dashboard();
           
            // System.out.println("sfsfsfs");
         } catch (IOException ex) {
             Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
    }    
    
}
