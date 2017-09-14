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
public class OrderViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    
       @FXML
    private JFXTreeTableView<OrderList> table;
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
                        JFXTreeTableColumn<OrderList,String>serial=new JFXTreeTableColumn<>("#");
       serial.setPrefWidth(200);
       serial.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderList, String> param) {
               return param.getValue().getValue().serial;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<OrderList,String>id=new JFXTreeTableColumn<>("ID");
       id.setPrefWidth(200);
       id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderList, String> param) {
               return param.getValue().getValue().id;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<OrderList,String>tax=new JFXTreeTableColumn<>("TAX");
       tax.setPrefWidth(200);
       tax.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderList, String> param) {
               return param.getValue().getValue().total_tax;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<OrderList,String>orderNum=new JFXTreeTableColumn<>("ORDER #");
       orderNum.setPrefWidth(200);
       orderNum.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderList, String> param) {
               return param.getValue().getValue().order_number;//To change body of generated methods, choose Tools | Templates.
           }
       });
            JFXTreeTableColumn<OrderList,String>total=new JFXTreeTableColumn<>("TOTAL");
       total.setPrefWidth(200);
       total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderList, String> param) {
               return param.getValue().getValue().total_price;//To change body of generated methods, choose Tools | Templates.
           }
       });
        JFXTreeTableColumn<OrderList,String>subTotal=new JFXTreeTableColumn<>("SUB TOTAL");
       subTotal.setPrefWidth(200);
       subTotal.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderList,String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderList, String> param) {
               return param.getValue().getValue().subtotal_price;//To change body of generated methods, choose Tools | Templates.
           }
       });
       ObservableList<OrderList>users=FXCollections.observableArrayList();
       for(int i=0; i<Shopify.O.size(); i++)
        {
            OrderList u=Shopify.O.get(i);
            users.add(u);
      
        }
          // s =new SimpleStringProperty(str);
      // users.add(new User("Fahad Khan","25","Software"));
      // users.add(new User("Kamran Subhani","25","Software"));
       final TreeItem<OrderList>root=new RecursiveTreeItem<OrderList>(users,RecursiveTreeObject::getChildren);
       table.getColumns().setAll(serial,id,orderNum,tax,subTotal,total);
       table.setRoot(root);
       table.setShowRoot(false);
                   }
               });
           }
      }).start();  
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
     ListData();
    }    
    
}
