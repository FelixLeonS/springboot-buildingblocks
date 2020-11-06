package com.stacksimplify.restservices.springbootbuildingblocks.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	//URI/hellWworld
	//GET
	//@RequestMapping(method = RequestMethod.GET, path = "/helloWSorld")
	@GetMapping("/helloworld1")
	public String HelloWorld() {
		
		return "Hello World!";
		
	}
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Leon","Steven","Toronto");
	}

}
