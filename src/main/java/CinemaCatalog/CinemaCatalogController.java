package CinemaCatalog;

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
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ServerAddress;





@RestController
public class CinemaCatalogController {
	
	@RequestMapping("/")
    public String index() 
    {
		try {  
            ServerAddress serverAddress = new ServerAddress("localhost",27017);  
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
            addrs.add(serverAddress);  
              
            //MongoCredential.createScramSha1Credential() 用戶, schema名稱,密碼 
            MongoCredential credential = MongoCredential.createScramSha1Credential("cinema", "Movies", "cinema".toCharArray());  
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
            credentials.add(credential);  
              
            // 通過認證連接MongoDB
            MongoClient mongoClient = new MongoClient(addrs,credentials);
              
            // 連接到資料庫
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Movie");  
            System.out.println("Connect to database successfully");
            return "Connect to database successfully";
        } catch (Exception e) {  
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return "Connect to database falily";
        }
		
		
        //return "success";
    }
	
	
	@RequestMapping(value = "getCinemaCatalog", method = RequestMethod.GET)
    public String getCinemaCatalog(@RequestParam("userID") String userID)
    {
    	return CinemaCatalog.getCinemaCatalog(userID);
    }
	
}
