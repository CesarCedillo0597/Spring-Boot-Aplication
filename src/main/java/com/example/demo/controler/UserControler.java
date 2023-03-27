package com.example.demo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;

import org.springframework.ui.Model;

@Controller
public class UserControler {
	
	@Autowired
	RoleRepository roleRepository;
	
	UserService userService;
	
	@Autowired
	public UserControler(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/userForm")
	public String userform(Model model) {
		model.addAttribute("userForm",new User());
		model.addAttribute("userList",userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}
		
	
}
