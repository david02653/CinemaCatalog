package CinemaCatalog;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="api-gateway")
public interface FeignInterface {
	
	@RequestMapping(value = "/ordering/getMovieFromOrderList?userID=1", method = RequestMethod.GET)
    public String getMovieByID(@RequestParam("userID") String userID);
	
}
