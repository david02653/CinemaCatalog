package CinemaCatalog;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="api-gateway")
public interface FeignInterface {
	
	@RequestMapping(value = "/ordering/getMovieFromOrderList", method = RequestMethod.GET)
    public String getMovieByID(@PathVariable("userID") String userID);
	
}
