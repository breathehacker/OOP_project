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

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class CustomerViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    
       @FXML
    private JFXTreeTableView<customerList> table;
       @FXML
       public void showData()
       {
           
       }
    @FXML
       @SuppressWarnings("empty-statement")
    public void ListData()
    {
         // Platform.runLater(runnable);
       System.out.println("YUp p");
      // Products p = new Products();
      new Thread(new Runnable(){
           @Override
           public void run() {
               Platform.runLater(new Runnable(){
                   @Override
                   public void run() {
                        JFXTreeTableColumn<customerList,String>serial=new JFXTreeTableColumn<>("#");
       serial.setPrefWidth(200);
       serial.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerList, String> param) {
               return param.getValue().getValue().serial;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<customerList,String>id=new JFXTreeTableColumn<>("ID");
       id.setPrefWidth(200);
       id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerList, String> param) {
               return param.getValue().getValue().id;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<customerList,String>email=new JFXTreeTableColumn<>("Email");
       email.setPrefWidth(200);
       email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerList, String> param) {
               return param.getValue().getValue().email;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<customerList,String>fname=new JFXTreeTableColumn<>("FirstName");
       fname.setPrefWidth(200);
       fname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerList, String> param) {
               return param.getValue().getValue().fname;//To change body of generated methods, choose Tools | Templates.
           }
       });
            JFXTreeTableColumn<customerList,String>lname=new JFXTreeTableColumn<>("LastName");
       lname.setPrefWidth(200);
       lname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerList, String> param) {
               return param.getValue().getValue().lname;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<customerList,String>created=new JFXTreeTableColumn<>("CreatedAT");
       created.setPrefWidth(200);
       created.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<customerList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<customerList, String> param) {
               return param.getValue().getValue().created_AT;//To change body of generated methods, choose Tools | Templates.
           }
       });
       ObservableList<customerList>users=FXCollections.observableArrayList();
       for(int i=0; i<Shopify.C.size(); i++)
        {
            customerList u=Shopify.C.get(i);
            users.add(u);
      
        }
          // s =new SimpleStringProperty(str);
      // users.add(new User("Fahad Khan","25","Software"));
      // users.add(new User("Kamran Subhani","25","Software"));
       final TreeItem<customerList>root=new RecursiveTreeItem<customerList>(users,RecursiveTreeObject::getChildren);
       table.getColumns().setAll(serial,id,email,fname,lname,created);
       table.setRoot(root);
       table.setShowRoot(false);
                   }
               });
           }
      }).start();  
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ListData();
    }    
    
}
