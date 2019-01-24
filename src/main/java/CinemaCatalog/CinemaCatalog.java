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

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

public class CinemaCatalog {
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
	
	
	public static String getMovieByID(String ID) {
		try {  
            
			System.out.println("MongoDBConnect to database begin");

            MongoClient mongoClient = MongoClients.create("mongodb://cinema:cinema@140.121.196.23:4118");
            

            MongoDatabase mongoDatabase = mongoClient.getDatabase("Movies");
            System.out.println("MongoDBConnect to database successfully");

            String result = "[";
            MongoCollection<Document> collection = mongoDatabase.getCollection("Movie");
            BasicDBObject whereQuery = new BasicDBObject();
        	whereQuery.put("_id", new ObjectId(ID));
            FindIterable<Document> fi = collection.find(whereQuery);
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
	
	public static String getNotification(String userID) 
	{
		String result = "";
		try {
			URL url = new URL("http://140.121.196.23:4102/getNotification?userID="+userID);
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000);
			result = xmlDoc.html();
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
			URL url = new URL("http://140.121.196.23:4105/newMovieOrdering?moviesID="+moviesID);
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
