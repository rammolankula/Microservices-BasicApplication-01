package ram.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ram.learn.consumer.ContractRestConsumer;

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
