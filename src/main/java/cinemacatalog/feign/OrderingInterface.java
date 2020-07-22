package cinemacatalog.feign;

import com.soselab.vmamvserviceclient.annotation.TargetVersion;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// Assign service version to VMAMVS (unforce)
//@TargetVersion("service version")
@FeignClient("ordering")
@TargetVersion("0.0.1-SNAPSHOT")
public interface OrderingInterface {

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    String index2();

    @RequestMapping(value = "/getMovieFromOrderList", method = RequestMethod.GET)
    String getMovieByID(@RequestParam("userID") String userID);

    @RequestMapping(value = "/newMovieOrdering", method = RequestMethod.GET)
    String orderingMovie(@RequestParam("userID") String userID, @RequestParam("moviesID") String moviesID);

/*    @RequestMapping(value = "/validate/prime-number", method = RequestMethod.GET)
    String checkOddAndEven(@RequestParam("number") Integer number);*/

/*    @RequestMapping(value = "/getSomething", method = RequestMethod.GET)
    String getSomething(@RequestParam("userID") String userID);*/

/*    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/simulateError", method = RequestMethod.GET)
    String simulateError(@RequestParam("number") Integer number);*/

}
