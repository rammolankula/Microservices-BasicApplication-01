package ram.learn.consumer;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ContractRestConsumer {

	
	//#1.AutoWire DiscoveryClient Object
	@Autowired
	DiscoveryClient clinet;
	
	
	public String getDataFromcontract() {
		//#.2 call getInstances method by passing serviceiD of provider
		//#.3 Returns List<SI> from eureka
		List<ServiceInstance> si=clinet.getInstances("CONTRACT-SERVICE");
		
		//#4.Read one ServiceInstance from list at index(0)
		ServiceInstance serviceinstance=si.get(0);
		
		//5.Read URI from SI
		URI uri=serviceinstance.getUri(); 
		
		//6. add path to URI
		String url=uri+"/contract/data";
		
		//7.RestTemplate call
		RestTemplate rt=new RestTemplate();
		
		String resp=rt.getForObject(url, String.class);
		
		return resp;
		
	}
	
	
}
