/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author fadikhan
 */
public class Order extends Shopify {
   public  void count() throws IOException {
       // System.out.println("LOLZ");
        try {
         //   System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(getStore()+"/orders/count.json");
         getRequest.addHeader("accept", "application/json");
         getRequest.addHeader("encodnig","UTF-8");
         HttpResponse response = httpClient.execute(getRequest);
         if (response.getStatusLine().getStatusCode() != 200) 
         {
              System.out.println("LOLZ");
             JOptionPane.showMessageDialog(null,response.getStatusLine().getStatusCode(),"ERROR",JOptionPane.PLAIN_MESSAGE);
         } 
         else
         {
         BufferedReader br = new BufferedReader(
         new InputStreamReader((response.getEntity().getContent()),"UTF-8"));

         String output;
         System.out.println("Output from Server .... \n");
         while ((output = br.readLine()) != null) {
          JSONObject obj = new JSONObject(output);
          setOrderNum(obj.getInt("count"));
         System.out.println(output);
         }
         }

         httpClient.getConnectionManager().shutdown();
         
         } 
        catch (ClientProtocolException e) {
             System.out.println("Connection Error");
         } 
        catch (IOException e) {
        System.out.println("Connection Error");
         }   
    }
   public void listOrders()
   {
              try {
         //   System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(getStore()+"/orders.json");
         getRequest.addHeader("accept", "application/json");
         getRequest.addHeader("encodnig","UTF-8");
         HttpResponse response = httpClient.execute(getRequest);
         if (response.getStatusLine().getStatusCode() != 200) 
         {
              System.out.println("LOLZ");
             JOptionPane.showMessageDialog(null,response.getStatusLine().getStatusCode(),"ERROR",JOptionPane.PLAIN_MESSAGE);
         } 
         else
         {
         BufferedReader br = new BufferedReader(
         new InputStreamReader((response.getEntity().getContent()),"UTF-8"));    
         String output;
         System.out.println("Output from Server .... \n");
         while ((output = br.readLine()) != null) {
          JSONObject obj = new JSONObject(output);
           JSONArray mainArr= obj.getJSONArray("orders");
          System.out.println(mainArr.length());
          int c=0;
          for(int i=0; i<mainArr.length(); i++)
          {
           String serial=(i+1)+"";
           JSONObject ob=mainArr.getJSONObject(i);
           String id =ob.getLong("id")+"";
           String total_price=ob.getDouble("total_price")+"";
           String subtotal_price=ob.getDouble("subtotal_price")+"";
           String total_tax=ob.getDouble("total_tax")+"";
           String currency=ob.getString("currency");
           String order_number=ob.getInt("order_number")+"";
          Shopify.O.add(new OrderList(serial,id,total_price,subtotal_price,total_tax,currency,order_number));
           System.out.println(id+"\n"+total_price+"\n"+subtotal_price+"\n"+total_tax+"\n"+currency+"\n"+order_number+"\n"+"\n");
          }
            
        
         }
         }

         httpClient.getConnectionManager().shutdown();
         
         } 
        catch (ClientProtocolException e) {
             System.out.println("Connection Error");
         } 
        catch (IOException e) {
        System.out.println("Connection Error");
         }  
   }
}
