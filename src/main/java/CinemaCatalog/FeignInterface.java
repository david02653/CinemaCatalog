package CinemaCatalog;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;

@FeignClient(name="api-gateway")
public interface FeignInterface {
	
	@RequestMapping(value = "/ordering/getMovieFromOrderList", method = RequestMethod.GET)
    public String getMovieByID(@RequestParam("userID") String userID);
	
	
	@RequestMapping(value = "/notification/getNotification", method = RequestMethod.GET)
    public String getNotification(@RequestParam("userID") String userID);
	
	
	@RequestMapping(value = "/ordering/newMovieOrdering", method = RequestMethod.GET)
    public String orderingMovie(@RequestParam("moviesID") String moviesID);
	
}
