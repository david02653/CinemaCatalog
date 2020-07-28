package cinemacatalog;

import cinemacatalog.feign.NotificationInterface;
import cinemacatalog.feign.OrderingInterface;
import com.soselab.vmamvserviceclient.annotation.FeignRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value = "CinemaCatalogController", tags = "與電影相關的所有一切都在這裡")
@RestController
public class CinemaCatalogController {

	@Autowired
	OrderingInterface orderingInterface;
	@Autowired
	NotificationInterface notificationInterface;


	/*@RequestMapping(value="/hi", method = RequestMethod.GET )
	public String home(@RequestParam("name") String name) {

		return orderingInterface.hello(name);
	}*/

/*	@FeignRequest(client = OrderingInterface.class, method = "checkOddAndEven", parameterTypes = Integer.class)
	@RequestMapping(value="/calculate", method = RequestMethod.GET)
	public String checkOddAndEven(@RequestParam("number") Integer number) {
		return orderingInterface.checkOddAndEven(number);
	}*/

	/*@RequestMapping(value="/simulateError", method = RequestMethod.GET)
	public String simulateError(@RequestParam("number") Integer number) {
		return orderingInterface.simulateError(number);
	}*/




	private static final Logger logger = LoggerFactory.getLogger(CinemaCatalogController.class);
	
/*	@ApiOperation(value = "測試此伺服器是否成功連線", notes = "成功連線就回傳success")
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String index() 
    {
    	logger.info("success");
		return "success";
    }*/

/*	@ApiOperation(value = "模擬404錯誤", notes = "會回傳404")
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/index2", method = RequestMethod.GET)
	public ResponseEntity<CinemaCatalog> index2()
	{

		return ResponseEntity.notFound().build();
	}


	// 模擬高duration
	@ApiOperation(value = "測試是否成功連線", notes = "成功連線就回傳success")
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/index3", method = RequestMethod.GET)
	public String index3()
	{

		String result = "wait success";


		long num = (long)(Math.random() * 30);
		try {
			Thread.sleep(num);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/


	@ApiOperation(value = "拿到所有的電影資料", notes = "回傳資料")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllMovies", method = RequestMethod.GET)
    public String getAllMovies()
    {
    	return CinemaCatalog.getAllMovies();
    }


	@FeignRequest(client = OrderingInterface.class, method = "getMovieByID", parameterTypes = String.class)
	@ApiOperation(value = "利用ID找到某個電影", notes = "回傳某電影資料")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getMovieByID", method = RequestMethod.GET)
    public String getMovieByID(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID)
    {
		String data = "";
		try {
			
			data = orderingInterface.getMovieByID(userID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return CinemaCatalog.getMovieByID(userID,data);
    }


	@FeignRequest(client = OrderingInterface.class, method = "orderingMovie", parameterTypes = {String.class, String.class})
	@ApiOperation(value = "購買電影", notes = "購買成功就回傳成功")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/orderingMovie", method = RequestMethod.GET)
    public String orderingMovie(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID, @ApiParam(required = true, name = "ID", value = "電影編號")@RequestParam("moviesID") String moviesID)
    {
		String result = "";
		try {
			
			result = orderingInterface.orderingMovie(userID, moviesID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
		
    }


	@FeignRequest(client = NotificationInterface.class, method = "getNotification", parameterTypes = String.class)
	@ApiOperation(value = "拿到所有通知", notes = "回傳通知資料")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getNotification", method = RequestMethod.GET)
	public String getNotification(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID)
	{

		String result = "";

		try {
			result = notificationInterface.getNotification(userID);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@FeignRequest(client = OrderingInterface.class, method = "getSomething", parameterTypes = String.class)
	@ApiOperation(value = "拿東西", notes = "拿東西")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getSomething", method = RequestMethod.GET)
	public String getSomething(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID)
	{
		String data = "";

		return orderingInterface.getSomething(userID);

	}

	
}



