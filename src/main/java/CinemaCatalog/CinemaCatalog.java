package CinemaCatalog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CinemaCatalog {
	
	@Autowired
	FeignInterface feignInterface;
	
	public static String getAllMovies() {
		try {  
            
			System.out.println("MongoDBConnect to database begin");
			
            MongoClient mongoClient = MongoClients.create("mongodb://cinema:cinema@140.121.196.23:4118");
            
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Movies");
            System.out.println("MongoDBConnect to database successfully");

            String result = "[";
            MongoCollection<Document> collection = mongoDatabase.getCollection("Movie");
            FindIterable<Document> fi = collection.find();
            MongoCursor<Document> cursor = fi.iterator();
            while(cursor.hasNext()) 
            {
            	result += cursor.next().toJson();
            	if(cursor.hasNext())
            		result += ",";
            }
            result += "]";
            System.out.println("Connect to database successfully");
            return result;
            
        } catch (Exception e) {  
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return "{}";
        }
	}
	
	
	public String getMovieByID(String userID) {
		String result = "";
		
		try {
			
			result = "[";
			
			
			URL url = new URL("http://140.121.196.23:4139/ordering/getMovieFromOrderList?userID=1");
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000);
			String jaStr = xmlDoc.select("body").get(0).text();
			
			
			//String jaStr = feignInterface.getMovieByID(userID);
			/*
			System.out.println("------------------------------------");
			System.out.println(feignInterface.getMovieByID(userID));
			System.out.println("------------------------------------");
			*/
			JSONArray jsonArray = JSONArray.fromObject(jaStr);
			
			
			System.out.println("MongoDBConnect to database begin");
			
            MongoClient mongoClient = MongoClients.create("mongodb://cinema:cinema@140.121.196.23:4118");
            
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Movies");
            System.out.println("MongoDBConnect to database successfully");
            
            MongoCollection<Document> collection = mongoDatabase.getCollection("Movie");
            
            
            for(int i = 0; i < jsonArray.size(); i++) {
            	JSONObject jsonObject = jsonArray.getJSONObject(i);
            	
            	BasicDBObject whereQuery = new BasicDBObject();
            	
            	whereQuery.put("_id", new ObjectId(jsonObject.getString("ObjectID")));
            	
                FindIterable<Document> fi = collection.find(whereQuery);
                MongoCursor<Document> cursor = fi.iterator();
                while(cursor.hasNext()) 
                {
                	result += cursor.next().toJson();
                }
            	
                if(i < jsonArray.size() - 1)
                	result += ",";
            }
			
			
			result += "]";
			
			return result;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
		
	}
	
	public static String getNotification(String ID) 
	{
		String result = "";
		
		try {
			URL url = new URL("http://140.121.196.23:4139/notification/getNotification?userID="+ID);
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000);
			result = xmlDoc.select("body").get(0).text();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static String orderingMovie(String moviesID)
	{
		String result = "";
		try {
			URL url = new URL("http://140.121.196.23:4139/ordering/newMovieOrdering?moviesID="+moviesID);
			URLConnection urlConnection = url.openConnection();
			
			
			BufferedReader in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()) );
			String current = "";
			while((current = in.readLine()) != null)
	         {
				result += current;
	         }
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
