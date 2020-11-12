package com.stacksimplify.restservices.springbootbuildingblocks.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	//URI/hellWworld
	//GET
	//@RequestMapping(method = RequestMethod.GET, path = "/helloWSorld")
	@Autowired
	private ResourceBundleMessageSource messageSoruce;
	@GetMapping("/helloworld1")
	public String HelloWorld() {
		
		return "Hello World!";
		
	}
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Leon","Steven","Toronto");
	}
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader (name = "Accept-Language",required = false) String locale) {
		
		return messageSoruce.getMessage("label.hello", null ,new Locale(locale));
		
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2() {
		
		return messageSoruce.getMessage("label.hello", null ,LocaleContextHolder.getLocale());
		
	}

}
