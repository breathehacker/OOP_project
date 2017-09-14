/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import static java.lang.ProcessBuilder.Redirect.from;
import static java.time.Clock.system;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javafxapplication8.Shopify.getStore;
import javax.swing.JOptionPane;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import static org.apache.http.client.methods.RequestBuilder.delete;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author fadikhan
 */
public class Products extends Shopify {
  
   public  void count() throws IOException {
       // System.out.println("LOLZ");
        try {
          //  System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(getStore()+"/products/count.json");
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
          setProductNum(obj.getInt("count"));
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
   public void listProducts()
   {
              try {
            //System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(getStore()+"/products.json");
         getRequest.addHeader("accept", "application/json");
         getRequest.addHeader("encodnig","UTF-8");
         HttpResponse response = httpClient.execute(getRequest);
         if (response.getStatusLine().getStatusCode() != 200) {
         throw new RuntimeException("Failed : HTTP error code : "
         + response.getStatusLine().getStatusCode());
         } else {
         System.out.println("response=" + response.getStatusLine());
         }

         BufferedReader br = new BufferedReader(
         new InputStreamReader((response.getEntity().getContent()),"UTF-8"));

         String output;
         System.out.println("Output from Server .... \n");
      
         while ((output = br.readLine()) != null) {
              String src=null;
           JSONObject obj = new JSONObject(output);
         System.out.println(output);
         // System.out.println(obj.getJSONArray("products").toString());
         JSONArray jsonMainArr = obj.getJSONArray("products");
          System.out.println(jsonMainArr.length());
          int c=0;
          
    for (int i = 0; i < jsonMainArr.length(); i++) {  // **line 2**
     JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
     try{
        JSONObject subObj = childJSONObject.getJSONObject("image");
      src = subObj.getString("src");
      System.out.println("Image SRC  ----->  " +src);  
     }
     catch (Exception e)
     {
         
     }
    
               
    // String id =childJSONObject.getString("tags");
      long ida =childJSONObject.getLong("id");
      String title=childJSONObject.getString("title");
      //System.out.println(childJSONObject);
      String vendor=childJSONObject.getString("vendor");
      String serial=""+(i+1);
      String id=""+ida;
      String body_html=childJSONObject.getString("body_html");
      String product_type=childJSONObject.getString("product_type");
      String created_at=childJSONObject.getString("created_at");
      String tags=childJSONObject.getString("tags");
      String published_scope=childJSONObject.getString("published_scope");
      String price=null;
      try{
           JSONArray jsonVariants = childJSONObject.getJSONArray("variants");
           JSONObject varChild = jsonVariants.getJSONObject(0);
           price=""+varChild.getDouble("price");
           
      }
      catch (Exception e){
          System.out.println(e);
      }
       System.out.println("Serial ---->  "+serial);
        System.out.println("body_html ---->  "+body_html);
        System.out.println("product_type ---> " +product_type);
        System.out.println("created_at ---> " +created_at);
        System.out.println("ID ---->  "+ida);
        System.out.println("Title ---> " +title);
        System.out.println("Vendor ---> " +vendor);
          System.out.println("SCope ---->  "+published_scope);
        System.out.println("tags ---> " +tags);
        System.out.println("price ---> " +price);
        l.add(new User(
                 serial,
                 id,
                 title,
                 tags,
                 vendor,
                 created_at,
                 price,
                 product_type,
                 src,
                 body_html,
                published_scope
        ));
         System.out.println(" ---___ ");
        ++c;
    // int age     = childJSONObject.getInt("age");
}
     System.out.println(c);
         }
         httpClient.getConnectionManager().shutdown();
         } catch (ClientProtocolException e) {
            e.printStackTrace();
             System.out.println("Connection Error");
         } catch (IOException e) {
        System.out.println("Connection Error");
         } 
   }
   public boolean delete(int index)
   {
       User u=Shopify.l.get(index);
       String value=u.id.get();
       HttpDelete delete = new HttpDelete(Shopify.getStore()+"products/"+value+".json");
       return false;
   }
   public boolean addProduct(
                String title,
                String tags,
                String vendor,
                String price,
                String product_type,
                String path,
                String htmlBody,
                String Scope
                   )
   {
       String attach=null;
       File file = new File(path);
   
   // File file = chooser.showOpenDialog(new Stage());
    System.out.println(file.getAbsolutePath()+"\n"+file.getPath());
 try (FileInputStream imageInFile = new FileInputStream(file)) {
                 
		
                String base64Image = "";
		byte imageData[] = new byte[(int) file.length()];
		imageInFile.read(imageData);
		base64Image = Base64.getEncoder().encodeToString(imageData);
                attach=base64Image;
                System.out.println(base64Image);
}
 catch (Exception e)
 {
     
 }
  System.out.println(htmlBody);
  
  int a=htmlBody.indexOf("<body contenteditable=\"true\">");
  int b=htmlBody.indexOf("</body>");
   System.out.println(a);
   System.out.println(b);
   String str=htmlBody.substring(a+"<body contenteditable=\"true\">".length(), b);
    System.out.println(htmlBody.substring(a+"<body contenteditable=\"true\">".length(), b));
htmlBody=stripHTMLTags(htmlBody);
 System.out.println(htmlBody);
       String toPost="{\n" +
"  \"product\": {\n" +
"    \"title\":\"" +title+"\",\n" +
"    \"body_html\":\"" +htmlBody+"\",\n" +
"    \"vendor\":\""+vendor+"\",\n" +
"    \"product_type\":\""+ product_type+"\",\n" +
"    \"price\":\""+price+"\",\n" +
"    \"scope\":\""+ Scope+"\",\n" +
 "    \"tags\":\""+ tags+"\",\n" +
"    \"images\": [\n" +
"      {\n" +
"        \"attachment\":\""+attach+"\"\n"+
"      }\n" +
"    ]\n" +
"  }\n" +
"}";
       System.out.println(toPost);
       HttpClient httpClient = new DefaultHttpClient();
boolean yes=false;
       try {
           StringEntity requestEntity = new StringEntity(toPost, "application/json","UTF-8"); 
HttpPost postMethod = new HttpPost(Shopify.getStore()+"/products.json");
System.out.println(Shopify.getStore());
postMethod.setEntity(requestEntity);
HttpResponse rawResponse = httpClient.execute(postMethod);
System.out.println(rawResponse.getStatusLine().getStatusCode()+"\n"+rawResponse.toString());
yes=true;
       } catch (UnsupportedEncodingException ex) {
           System.out.println(ex);
           return yes;
       } catch (IOException ex) {
            System.out.println(ex);
            return yes;
       }
       return yes;
   }
   public boolean update(
                String id,
                 String title,
                String tags,
                String vendor,
                String price,
                String product_type,
                String htmlBody,
                String Scope
   
   )
   {
       System.out.println(htmlBody);
  
  int a=htmlBody.indexOf("<body contenteditable=\"true\">");
  int b=htmlBody.indexOf("</body>");
   System.out.println(a);
   System.out.println(b);
   String str=htmlBody.substring(a+"<body contenteditable=\"true\">".length(), b);
    System.out.println(htmlBody.substring(a+"<body contenteditable=\"true\">".length(), b));
htmlBody=stripHTMLTags(htmlBody);
 System.out.println(htmlBody);
       String toPost="{\n" +
"  \"product\": {\n" +
 "    \"id\":" +id+",\n" +
"    \"title\":\"" +title+"\",\n" +
"    \"tags\":\"" +tags+"\",\n" +
 "    \"scope\":\"" +Scope+"\",\n" +  
   "    \"html_body\":\"" +htmlBody+"\",\n" +
  "    \"price\":\"" +price+"\"\n" +
"  }\n" +
"}";
       System.out.println(toPost);
       HttpClient httpClient = new DefaultHttpClient();
boolean yes=false;
       try {
           HttpPut request = new HttpPut(Shopify.getStore()+"/products/"+id+".json");
        StringEntity params =new StringEntity(toPost,"UTF-8");
        params.setContentType("application/json");
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "*/*");
        request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
        request.addHeader("Accept-Language", "en-US,en;q=0.8");
        request.setEntity(params);
           StringEntity requestEntity = new StringEntity(toPost, "application/json","UTF-8"); 
HttpPut postMethod = new HttpPut(Shopify.getStore()+"/products/"+id+".json");
System.out.println(Shopify.getStore()+"/products/"+id+".json");
postMethod.setEntity(requestEntity);
HttpResponse rawResponse = httpClient.execute(request);
System.out.println(rawResponse.getStatusLine().getStatusCode()+"\n"+rawResponse.toString());
yes=true;
       } catch (UnsupportedEncodingException ex) {
           System.out.println(ex);
           return yes;
       } catch (IOException ex) {
            System.out.println(ex);
            return yes;
       }
    return yes;   
   }
    private String stripHTMLTags(String htmlText) {

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer sb = new StringBuffer(htmlText.length());
        while(matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString().trim());
        return sb.toString().trim();

    }
    public boolean delete(String value)
    {
          HttpClient httpClient = new DefaultHttpClient();
boolean yes=false;
       try {
          // StringEntity requestEntity = new StringEntity(toPost, "application/json","UTF-8"); 
HttpDelete postMethod = new HttpDelete(Shopify.getStore()+"/products/"+value+".json");
System.out.println(Shopify.getStore()+"/products/"+value+".json");
HttpResponse rawResponse = httpClient.execute(postMethod);

System.out.println(rawResponse.getStatusLine().getStatusCode()+"\n"+rawResponse.toString());
yes=true;
       } catch (UnsupportedEncodingException ex) {
           System.out.println(ex);
           return yes;
       } catch (IOException ex) {
            System.out.println(ex);
            return yes;
       }
        return yes;
    }
}
