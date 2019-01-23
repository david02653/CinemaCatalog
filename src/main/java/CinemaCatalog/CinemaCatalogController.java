package CinemaCatalog;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class CinemaCatalogController {
	@CrossOrigin(origins = "*")
	@RequestMapping("/")
    public String index() 
    {
		return "success";
    }
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getAllMovies", method = RequestMethod.GET)
    public String getAllMovies()
    {
    	return CinemaCatalog.getAllMovies();
    }
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getMovieByID", method = RequestMethod.GET)
    public String getMovieByID(@RequestParam("ID") String ID)
    {
    	return CinemaCatalog.getMovieByID(ID);
    }
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getNotification", method = RequestMethod.GET)
    public String getNotification(@RequestParam("userID") String userID)
    {
    	return CinemaCatalog.getNotification(userID);
    }
	
}



