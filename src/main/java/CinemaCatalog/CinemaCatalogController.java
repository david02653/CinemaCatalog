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

@RestController
public class CinemaCatalogController {
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		return new ModelAndView("hello"); // 根據view resolver mapping至hello.jsp
	}
	
	
	@RequestMapping(value = "getCinemaCatalog", method = RequestMethod.GET)
    public String getCinemaCatalog(@RequestParam("userID") String userID)
    {
    	return CinemaCatalog.getCinemaCatalog(userID);
    }
    
    @RequestMapping("/test") 
    void test(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException 
    {
    	RequestDispatcher view;
    	view = request.getRequestDispatcher("page.html");
		view.forward(request, response);
    }
	
}
