package cinemacatalog.feign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ordering")
public interface OrderingInterface {

    @RequestMapping(value = "/getMovieFromOrderList", method = RequestMethod.GET)
    String getMovieByID(@RequestParam("userID") String userID);

    @RequestMapping(value = "/newMovieOrdering", method = RequestMethod.GET)
    String orderingMovie(@RequestParam("moviesID") String moviesID);

}
