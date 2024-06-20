package ram.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractRestController {
	
	@GetMapping("/data")
	public String getData() {
		return "FROM VENDOR";
		
	}

}
