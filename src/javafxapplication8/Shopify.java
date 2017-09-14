package javafxapplication8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author fadikhan
 */
public class  Shopify {
    private boolean connection;
    private String Key;
      private String Pass;
      private static String store;
      private  static int orders_n;
       private  static int products_n;
        private  static int customers_n;
        public static List<User> l;
        public static int index;
        public  static List<OrderList> O;
        public static String productView;
          public static List<customerList> C;
      //  private 
        public  List<User>  getproducts()
        {
           return l; 
        }
      public Shopify()
      {
          //System.out.println("SUb!");
          l=  Collections.synchronizedList(new ArrayList<User>());
          O= Collections.synchronizedList(new ArrayList<OrderList>());
          C= Collections.synchronizedList(new ArrayList<customerList>());
          
      }
      public static void setOrderNum(int n)
        {
            orders_n=n;
        }
       public static void setProductNum(int n)
        {
            products_n=n;
        }
        public static void setCustomerNum(int n)
        {
            customers_n=n;
        }
       public static int getOrderNum()
        {
           return orders_n;
        }
       public static int getProductNum()
        {
          return  products_n;
        }
        public static int getCustomerNum()
        {
            return customers_n;
        }
   public Shopify(String Key,String Pass) throws IOException {
       this.Key=Key;
       this.Pass=Pass;
       String storeName="itshedit.myshopify.com";
        store="https://"+Key+":"+Pass+"@"+storeName+"/admin";
      // String sJava="lorem \\u003cmeta charset=\\\"utf-8\\\"\\u003e\\u003cspan\\u003elorem\\u003c\\/span\\u003e\\u003cmeta charset=\\\"utf-8\\\"\\u003e\\u003cspan\\u003elorem\\u003c\\/span\\u003e\\u003cmeta ";
     //System.out.println("StringEscapeUtils.unescapeJava(sJava):\n" );
    
        try {
          //  System.out.println("LOLZ");
        // DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = new DefaultHttpClient();
         HttpGet getRequest = new HttpGet(store+"/orders.json");
          System.out.println("Yes!");
         getRequest.addHeader("accept", "application/json");
         getRequest.addHeader("encodnig","UTF-8");
      
         HttpResponse response = httpClient.execute(getRequest);
          System.out.println(response.getStatusLine().getStatusCode());
          if(200==response.getStatusLine().getStatusCode()){
               connection=true;
               System.out.println("Yes!");
          }
        
         } 
        catch (IOException e) {
        connection=false;
         }
        
    }
     protected boolean isConnection(){
          return connection; 
       } 
     protected static String getStore(){
          return store; 
       } 
}

