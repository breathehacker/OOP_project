/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static javafxapplication8.Shopify.getStore;
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
public class Customer extends Shopify {
  
   public  void count() throws IOException {
       // System.out.println("LOLZ");
        try {
          //  System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(getStore()+"/customers/count.json");
         getRequest.addHeader("accept", "application/json");
         getRequest.addHeader("encodnig","UTF-8");
         HttpResponse response = httpClient.execute(getRequest);
         if (response.getStatusLine().getStatusCode() != 200) 
         {
            //  System.out.println("LOLZ");
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
          setCustomerNum(obj.getInt("count"));
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
   
    public void listCustomer()
   {
              try {
         //   System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(Shopify.getStore()+"/customers.json");
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
         String str="";
         System.out.println("Output from Server .... \n");
         while ((output = br.readLine()) != null) {
         str=str+output;
         }
          JSONObject obj = new JSONObject(str);
          System.out.println(str);
           JSONArray mainArr= obj.getJSONArray("customers");
          System.out.println(mainArr.length());
          int c=0;
          for(int i=0; i<mainArr.length(); i++)
          {
           String serial=(i+1)+"";
           JSONObject ob=mainArr.getJSONObject(i);
           String id =ob.getLong("id")+"";
           String email=ob.getString("email")+"";
           String created_At=ob.getString("created_at");
           String fname=ob.getString("first_name");
           String lname=ob.getString("last_name");
           Shopify.C.add(new customerList(serial,id,email,fname,lname,created_At));
        //  Shopify.O.add(new OrderList(serial,id,total_price,subtotal_price,total_tax,currency,order_number));
           System.out.println(id+"\n"+email+"\n"+fname+"\n"+lname+"\n"+created_At);
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
