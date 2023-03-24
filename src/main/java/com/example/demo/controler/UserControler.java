package com.example.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserControler {

	@GetMapping("/")
	public String inder() {
		return "index";
	}
	
	@GetMapping("/userForm")
	public String userform() {
		return "user-form/user-view";
	}
		
	
}
