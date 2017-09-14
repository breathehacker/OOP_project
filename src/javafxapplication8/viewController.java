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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Ass hole
 */
public class viewController implements Initializable {
    private  Shopify s;
   @FXML
    private TextField API_key;
    
    @FXML
    private TextField API_pass;
    @FXML
    private JFXSpinner img;
    @FXML
            
    void config(ActionEvent event) throws IOException {
       //String st=new String("Key");
      String st=API_key.getText().trim();
      //  String pass=new String("pass").trim();
     String  pass=API_pass.getText().trim();
       if(!st.isEmpty() && !pass.isEmpty())
           {
             
             Platform.runLater(new Runnable(){
                 @Override
                 public void run() {
                    img.setVisible(true);
                 }
             });
             
             
             new Thread(new Runnable(){
                 @Override
                 public void run() {
                     try {
                         s = new Shopify(st,pass);
                 if(!s.isConnection()){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                         JOptionPane.showMessageDialog(null,"NO Internet OR Incorrect credentials","ERROR",JOptionPane.PLAIN_MESSAGE);
                         img.setVisible(false);
                    }
                
                
                });
                
                
           
        }
            else{
                     Platform.runLater(new Runnable(){
                         @Override
                         public void run() {
                             JOptionPane.showMessageDialog(null,Shopify.getStore(),"Success",JOptionPane.PLAIN_MESSAGE);
            try{
                AnchorPane fxm=FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                Parent p=(Parent)fxm;
                Stage stage= new Stage();
                stage.setScene(new Scene(p));
                stage.setTitle("Hedit");
                stage.getIcons().add(new Image("/logo_3.png"));
                stage.show();
               // Parent root=img.getParent();
                 img.getScene().getWindow().hide();
            }
            catch(Exception e)
            {
                        System.out.println(e);
            } 
                         }
                     
                     
                     });
       }
                     } catch (IOException ex) {
                       
                     }
                 }
             
             }).start();

           

           }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("VPOD");
        API_key.setText("1a8b9f2c405628adf089f4861f303cb2");
         API_pass.setText("2ce7cd6ef5e7d93d19def6c06cbd920e");
        img.setVisible(false);
    }

}
