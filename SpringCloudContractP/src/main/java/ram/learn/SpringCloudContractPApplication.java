package ram.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudContractPApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudContractPApplication.class, args);
	}

}
