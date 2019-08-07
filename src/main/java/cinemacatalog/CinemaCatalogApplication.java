package cinemacatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import com.soselab.vmamvserviceclient.annotation.EnableVmamvClient;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableVmamvClient
public class CinemaCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaCatalogApplication.class, args);
	}

}

