# MicroservicesBasicApplication
This repository contains a basic microservices application developed using Spring Boot,The project demonstrates the architecture and functionality of microservices, including a Provider service, a Consumer service, and a Eureka Server
 <pre>
 &#8594;  A.Eureka Server(Spring Cloud NetFlix): Behaves like <b>R&D</b> Server
 &#8594;  B.Provider Microservice(Spring Cloud +Spring Boot Rest)
 &#8594;  C.Consumer Microservices(Spring Cloud +Spring Boot Rest)
 </pre>
 <pre>
 <b>Service Instance:</b> If we can run Provider/Consumer Application one time using server then one instance is created
 one instance details give to R&D server which is called one service instance(Service[ApplicationName],InstanceId,
 IP,PORT,LF).
 <b>app run 1-time AND publish 1-serviceinstance</b>
 </pre>
 #### CODE FLOW
 <pre>
   1.Eureka server application configuration
   2.Write provider application with restcontroller
   2.Publish Application to eureka server
   3.Write Consumer application with rest controller
   4.Publish application to eureka server
   5.Write client code to fetch/find provider serviceInstance to make HTTP call
 </pre>

 <pre>
   *)Spring Cloud has provided below clients to perform
   FIND(with Eureka) and HTTP call (to Provider App)
 &#8594; a.DiscoveryClient (Basic Client + No Load Balance Support)
 &#8594; b.LoadBalancer Client(Load Balance Support)
 &#8594; c.**FeignClient(Declariative/Interface/Abstract client)
 </pre>
*)ServiceInstance details-ServiceID(Application Name),InstanceId(Instance Number),
                                    HOST details(IP,PORT,LF,,,,)..etc
#### Discovery Client
<pre>
 <b>Discovery Client is provided by spring cloud this is used to find the serviceIntstance details from the
 EurekaServer based on serviceID</b>
 &#8594; It will fetch multiple service-instances as List.But not supporting loadBalancer.
 &#8594; manully we have to choose one serviceInstances and read  URI,later add path(URL) and make call using RestTemplate
</pre>
##### Eureka Server with microservices using DiscoveryClient
<pre>
Name:SpringCloudEurekaServer
Dep:EurekaServer 
application.properties
server.port=8761
#publish
eureka.client.register-with-eureka=false
#Find
eureka.client.fetch-registry=false
</pre>
##### Code Flow
<pre>
<b>2.Provider Application</b>
Name: SpringCloudContractService
Dep:  Spring Web,Eureka Discovery Client
server.port=9900
spring.application.name=CONTRACT-SERVICE
#eureka Location for publish
eureka.client.service-url.default-zone=http://localhost:8761/eureka
<b>Controller</b>
  @RestController
@RequestMapping("/contract")
public class ContractRestController {
	@GetMapping("/data")
	public String getData() {
		return "FROM CONTRACT:";	
	}
}
<b>Consumer Application</b>
==========================
<b>Consumer Controller</b>
@RestController
@RequestMapping("/vendor")
public class VendorRestController {
	@Autowired
	private ContractRestConsumer consumer;
	@GetMapping("/info")
	public String getData()
	{
		return "FROM CONTRACT TO=>"+consumer.getDataFromcontract();
	}
}  
  <b>Consumer Class to connect provider application</b>
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
		
		//8.set the  
		String resp=rt.getForObject(url, String.class);
		
		return resp;
	}
}
</pre>
##### EXECUTION FLOW
<pre>
1.Start Eureka Server
2.Enter URL: http://localhost:8761/
3.start provider(ContractSevice) application
4.start consumer(Vendor Service) application
5.Refresh Eureka URL
6.Click on VendorService Link
7.http://learn:9901/vendor/info
</pre>
