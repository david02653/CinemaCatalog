package CinemaCatalog;

import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Api(value = "CinemaCatalogController", tags = "與電影相關的所有一切都在這裡")
@RestController
public class CinemaCatalogController {
	
	@Autowired
	FeignInterface feignInterface;
	
	@ApiOperation(value = "測試此伺服器是否成功連線", notes = "成功連線就回傳success")
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String index() 
    {
		return "success";
    }
	
	@ApiOperation(value = "拿到所有的電影資料", notes = "回傳資料")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllMovies", method = RequestMethod.GET)
    public String getAllMovies()
    {
    	return CinemaCatalog.getAllMovies();
    }
	
	@ApiOperation(value = "利用ID找到某個電影", notes = "回傳某電影資料")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getMovieByID", method = RequestMethod.GET)
    public String getMovieByID(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID)
    {
		return CinemaCatalog.getMovieByID(userID,feignInterface.getMovieByID(userID));
    }
	
	@ApiOperation(value = "拿到所有通知", notes = "回傳通知資料")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getNotification", method = RequestMethod.GET)
    public String getNotification(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID)
    {
    	//return CinemaCatalog.getNotification(userID);
		String result = "";
		
		try {
			/*
			URL url = new URL("http://140.121.196.23:4139/notification/getNotification?userID="+userID);
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000);
			result = xmlDoc.select("body").get(0).text();
			*/
			result=feignInterface.getNotification(userID);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
    }
	
	@ApiOperation(value = "購買電影", notes = "購買成功就回傳成功")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/orderingMovie", method = RequestMethod.GET)
    public String orderingMovie(@ApiParam(required = true, name = "ID", value = "電影編號")@RequestParam("moviesID") String moviesID)
    {
    	//return CinemaCatalog.orderingMovie(moviesID);
		
		String result = "";
		try {
			/*
			URL url = new URL("http://140.121.196.23:4139/ordering/newMovieOrdering?moviesID="+moviesID);
			URLConnection urlConnection = url.openConnection();
			
			
			BufferedReader in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()) );
			String current = "";
			while((current = in.readLine()) != null)
	         {
				result += current;
	         }
			*/
			
			result = feignInterface.orderingMovie(moviesID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
		
    }
	
}



