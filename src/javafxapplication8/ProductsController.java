/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTablePosition;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.I;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class ProductsController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    
       @FXML
    private JFXTreeTableView<User> table;
         @FXML
    void add(ActionEvent event) {
         Shopify.productView="add";
         ChangeView();
    }

    @FXML
    void del(ActionEvent event) {
        List<User>li=new ArrayList<User>(Shopify.l);
        System.out.println(Shopify.l.size());
      
                
         TreeTablePosition<User, String> pos = (TreeTablePosition<User, String>) table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        System.out.println(Shopify.l.size());
        Shopify.index=index;
         System.out.println(Shopify.l.size());
        
        System.out.println(index);
         System.out.println(Shopify.l.size());
         User u=Shopify.l.get(index);
          System.out.println(Shopify.l.size());
         String value=u.id.get();
            
                System.out.println(index);
        Products p=new Products();
                  System.out.println(Shopify.l.size());
                 boolean check=false;
                check= p.delete(value);
                 System.out.println("------>"+Shopify.l.size());
                     if(!check)
               {
                 Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         JOptionPane.showMessageDialog(null,"Error while Deleting","Error",JOptionPane.PLAIN_MESSAGE);
                     }
                 });  
               }
               else{
                    Platform.runLater(new Runnable(){
                     @Override
                     public void run() {
                         JOptionPane.showMessageDialog(null,"Success! Deleted Product","Delete!",JOptionPane.PLAIN_MESSAGE);
                      

                     }
                 }); 
               }
     
              System.out.println(Shopify.l.size());
             Shopify.l=li;
              System.out.println(Shopify.l.size());
    }
    @FXML
       @SuppressWarnings("empty-statement")
    void showData(ActionEvent event) {
          Shopify.productView="edit";
        new Thread(new Runnable(){
            @Override
            public void run() {
         TreeTablePosition<User, String> pos = (TreeTablePosition<User, String>) table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        Shopify.index=index;
        System.out.println(index);
                System.out.println(index);
                System.out.println(Shopify.l.size());
              
            }
        
        }).start();
      
                
             ChangeView();

    }
  public void ChangeView()
  {
       Platform.runLater(new Runnable(){
           @Override
           public void run() {
               try {
                   AnchorPane fxm=FXMLLoader.load(getClass().getResource("productEdit.fxml"));
                   Parent p=(Parent)fxm;
                   Stage stage= new Stage();
                   stage.setScene(new Scene(p));
                    stage.setOnCloseRequest(e -> {
                     stage.close();
                });
      
                   stage.setTitle("Hedit");
                   stage.getIcons().add(new Image("/logo_3.png"));
                   
                   stage.show();
                  
               } catch (IOException ex) {
                   Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
                
                
                });
  }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Platform.runLater(runnable);
       System.out.println("YUp p");
      // Products p = new Products();
      new Thread(new Runnable(){
           @Override
           public void run() {
               Platform.runLater(new Runnable(){
                   @Override
                   public void run() {
                        JFXTreeTableColumn<User,String>serial=new JFXTreeTableColumn<>("#");
       serial.setPrefWidth(50);
       serial.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().serial;//To change body of generated methods, choose Tools | Templates.
           }
       });
    JFXTreeTableColumn<User,String>id=new JFXTreeTableColumn<>("ID");
       id.setPrefWidth(130);
       id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().id;//To change body of generated methods, choose Tools | Templates.
           }
       });
         JFXTreeTableColumn<User,String>title=new JFXTreeTableColumn<>("TITLE");
       title.setPrefWidth(200);
       title.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().title;//To change body of generated methods, choose Tools | Templates.
           }
       });
       JFXTreeTableColumn<User,String>product_type=new JFXTreeTableColumn<>("PRODUCT TYPE");
       product_type.setPrefWidth(200);
       product_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().product_type;//To change body of generated methods, choose Tools | Templates.
           }
       });
       JFXTreeTableColumn<User,String>vendor=new JFXTreeTableColumn<>("VENDOR");
       vendor.setPrefWidth(200);
       vendor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().vendor;//To change body of generated methods, choose Tools | Templates.
           }
       });
       JFXTreeTableColumn<User,String>created_at=new JFXTreeTableColumn<>("CREATED AT");
       created_at.setPrefWidth(200);
       created_at.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().createdAt;//To change body of generated methods, choose Tools | Templates.
           }
       });
       JFXTreeTableColumn<User,String>scope=new JFXTreeTableColumn<>("SCOPE");
       scope.setPrefWidth(120);
       scope.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().scope;//To change body of generated methods, choose Tools | Templates.
           }
       });
       JFXTreeTableColumn<User,String>tags=new JFXTreeTableColumn<>("TAGS");
       tags.setPrefWidth(200);
       tags.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().tags;//To change body of generated methods, choose Tools | Templates.
           }
       });
       JFXTreeTableColumn<User,String>price=new JFXTreeTableColumn<>("PRICE");
       price.setPrefWidth(200);
       price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
               return param.getValue().getValue().price;//To change body of generated methods, choose Tools | Templates.
           }
       });
       ObservableList<User>users=FXCollections.observableArrayList();
       for(int i=0; i<Shopify.l.size(); i++)
        {
            User u=Shopify.l.get(i);
            users.add(u);
            System.out.println(u.getSerial());
      System.out.println(u.getprice());
        }
          // s =new SimpleStringProperty(str);
      // users.add(new User("Fahad Khan","25","Software"));
      // users.add(new User("Kamran Subhani","25","Software"));
       final TreeItem<User>root=new RecursiveTreeItem<User>(users,RecursiveTreeObject::getChildren);
       table.getColumns().setAll(serial,id,title,vendor,created_at,price,product_type,scope,tags);
       table.setRoot(root);
       table.setShowRoot(false);
                   }
               
               
               });
           }
      
      
      
      }).start();
      
    }    
    
}
