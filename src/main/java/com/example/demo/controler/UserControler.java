package com.example.demo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ViewResolver;

import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

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
	public String userForm(Model model) {
		model.addAttribute("userForm",new User());
		model.addAttribute("userList",userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm",user);
			model.addAttribute("formTab","active");
		}else {
			try {
				userService.createUser(user);
				model.addAttribute("userForm",user);
				model.addAttribute("listTab","active");
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm",user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList",userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
			
		}
		
		
		model.addAttribute("userList",userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		return "user-form/user-view";
	}
		
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name = "id")long id) throws Exception{
		User userToEdit = userService.getUserById(id);
		model.addAttribute("userForm",userToEdit);
		model.addAttribute("userList",userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("formTab","active");		
		model.addAttribute("editMode","true");
		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm",user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm",user);
				model.addAttribute("listTab","active");
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm",user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList",userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
				model.addAttribute("editMode","true");
			}
			
		}
		
		
		model.addAttribute("userList",userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		return "user-form/user-view";
	}
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name = "id")long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return userForm(model);
	}
	
}
