package CinemaCatalog;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.model.Filters.*;





@RestController
public class CinemaCatalogController {
	
	@RequestMapping("/")
    public String index() 
    {
		try {  
            
			System.out.println("MongoDBConnect to database begin");
            //連線到MongoDB服務 如果是遠端連線可以替換“localhost”為伺服器所在IP地址
			
            //MongoCredential.createScramSha1Credential()三個引數分別為 使用者名稱 資料庫名稱 密碼
            MongoCredential credential = MongoCredential.createScramSha1Credential("cinema", "Movies", "cinema".toCharArray());
            /*List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);*/

            MongoClientSettings settings = MongoClientSettings.builder()
                    .credential(credential)
                    .applyToSslSettings(builder -> builder.enabled(true))
                    .applyToClusterSettings(builder -> 
                        builder.hosts(Arrays.asList(new ServerAddress("140.121.196.23", 4118))))
                    .build();

            
            //通過連線認證獲取MongoDB連線
            MongoClient mongoClient = MongoClients.create(settings);
            
            //連線到資料庫(schema)
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Movies");
            System.out.println("MongoDBConnect to database successfully");

            //建立集合
//            mongoDatabase.createCollection("test");
//            System.out.println("集合建立成功");
//選擇集合
            MongoCollection collection = mongoDatabase.getCollection("Movie");
            
            Document dc = ((Document)collection.find().first());
            
            //System.out.println("Connect to database successfully");
            //return "Connect to database successfully:\n" + ((Document)collection.find().first()).toJson();
            return "Connect to database successfully:\n" + collection.find();
            
        } catch (Exception e) {  
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return "Connect to database Unsuccessfully: "+e;
        }
		
		
        //return "success";
    }
	
	
	@RequestMapping(value = "getCinemaCatalog", method = RequestMethod.GET)
    public String getCinemaCatalog(@RequestParam("userID") String userID)
    {
    	return CinemaCatalog.getCinemaCatalog(userID);
    }
	
	SingleResultCallback<Document> printDocument = new SingleResultCallback<Document>() {
	    @Override
	    public void onResult(final Document document, final Throwable t) {
	        System.out.println(document.toJson());
	    }
	};
	Block<Document> printDocumentBlock = new Block<Document>() {
	    @Override
	    public void apply(final Document document) {
	        System.out.println(document.toJson());
	    }
	};
	
}



