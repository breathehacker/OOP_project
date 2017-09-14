/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class ProductEditController implements Initializable {
 @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField vendor;

    @FXML
    private JFXTextField scope;

    @FXML
    private JFXTextField tags;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField productType;

    @FXML
    private HTMLEditor html;
    
    @FXML
    private Label id;

    @FXML
    private ImageView img;
@FXML
    private JFXButton theBtn;
    String path;
     String titleP;
           String vendorP;
           // String titleP=id.getText();
            String scopeP;
            String tagsP;
           String priceP;
           String type;
           String htmlText;
             boolean check;
   // theBtn.get
              String idP;
    @FXML
    void update(ActionEvent event) {
            if(Shopify.productView.equals("edit"))
            {
              System.out.println("Update!");
              titleP=title.getText();
            vendorP= vendor.getText();
            idP=id.getText();
             scopeP=scope.getText();
             tagsP=tags.getText();
            priceP= price.getText();
            type= productType.getText();
            htmlText= html.getHtmlText().toString();
            
             new Thread(new Runnable(){
             @Override
      
             public void run() {
                 Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         spinner.setVisible(true);
                     }
                 });
                 
                 
                Products p=new Products();
               check= p.update( idP,titleP, tagsP, vendorP, priceP,type, htmlText, scopeP);
                Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                        spinner.setVisible(false);
                     }
                 });  
               if(!check)
               {
                 Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         JOptionPane.showMessageDialog(null,"Error while Updating","Error",JOptionPane.PLAIN_MESSAGE);
                     }
                 });  
               }
               else{
                    Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         JOptionPane.showMessageDialog(null,"Success! Updated Product","Added!",JOptionPane.PLAIN_MESSAGE);
                     }
                 }); 
               }
               
             }

           }).start();
            
            
            
            }
            else if(Shopify.productView.equals("add"))
            {
          titleP=title.getText();
            vendorP= vendor.getText();
           // String titleP=id.getText();
             scopeP=scope.getText();
             tagsP=tags.getText();
            priceP= price.getText();
            type= productType.getText();
            htmlText= html.getHtmlText().toString();
            
            
            
            
          //  html.getc
            // Image i= img.getImage(); 
           //String path=i.getClass().
         
           
           new Thread(new Runnable(){
             @Override
      
             public void run() {
                 Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         spinner.setVisible(true);
                     }
                 });
                 
                 
                Products p=new Products();
               check= p.addProduct( titleP, tagsP, vendorP, priceP,type,path, htmlText, scopeP);
                Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                        spinner.setVisible(false);
                     }
                 });  
               if(!check)
               {
                 Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         JOptionPane.showMessageDialog(null,"Error while adding","Error",JOptionPane.PLAIN_MESSAGE);
                     }
                 });  
               }
               else{
                    Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         JOptionPane.showMessageDialog(null,"Success! Added Product","Added!",JOptionPane.PLAIN_MESSAGE);
                     }
                 }); 
               }
               
             }

           }).start();
            }
            else if(Shopify.productView.equals("del"))
            {
                
            }
    }
          @FXML
    private JFXButton picker;
    @FXML
    public void Choosefile()
    {
        if(Shopify.productView.equals("add"))
        {
              FileChooser fileChooser = new FileChooser();
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Img files (*.jpg", "*.jpg","*.png","*.gif","*.jpeg");
              fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(new Stage());
    fileChooser.setTitle("Choose image");
   // File file = chooser.showOpenDialog(new Stage());
    System.out.println(file.getAbsolutePath()+"\n"+file.getPath());
    //img.setImage(new Image(file.getAbsolutePath()));
    path=file.getAbsolutePath();
    Platform.runLater(new Runnable(){
                  @Override
                  public void run() {
                      System.out.println(file.getAbsolutePath());
                      try {
                          img.setImage(new Image(Paths.get(file.getAbsolutePath()).toUri().toURL().toString()));
                          picker.setVisible(false);
                      } catch (MalformedURLException ex) {
                          Logger.getLogger(ProductEditController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
    
    
    });
        }

    }
    
    @FXML
    private JFXSpinner spinner;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // anc.setVisible(false);
        System.out.println(Shopify.productView);
        spinner.setVisible(false);
        if(Shopify.productView.equals("edit"))
        {
            System.out.println(Shopify.index);
        title.setText(Shopify.l.get(Shopify.index).title.get());
        vendor.setText(Shopify.l.get(Shopify.index).vendor.get());
         id.setText(Shopify.l.get(Shopify.index).id.get());
          scope.setText(Shopify.l.get(Shopify.index).scope.get());
           tags.setText(Shopify.l.get(Shopify.index).tags.get());
            price.setText(Shopify.l.get(Shopify.index).price.get());
            productType.setText(Shopify.l.get(Shopify.index).product_type.get());
            html.setHtmlText(Shopify.l.get(Shopify.index).htmlBody.get());
            img.setImage(new Image(Shopify.l.get(Shopify.index).imageSrc.get())); 
            picker.setVisible(false);
        }
        else if(Shopify.productView.equals("add"))
        {
           theBtn.setText("Add");
           id.setText(" ");
           
        }
   
    }    

    
    
}
