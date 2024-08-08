package it.alessiolaneve.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.alessiolaneve.platform.model.User;
import it.alessiolaneve.platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/create")
	public String create(Model model) {
		List<User> allUsers = userRepository.findAll();
	    model.addAttribute("user", new User());
	    model.addAttribute("users", allUsers);
	    
	    return "/users/create";
	}
	
	@PostMapping("/create")
	public String store(
		@Valid @ModelAttribute("user") User newUser,
	   BindingResult bindingResult,
	   Model model){
		
	   if(bindingResult.hasErrors()) {
	      return "/users/create";
	   }

	   userRepository.save(newUser);

	   return "redirect:/users/index";
	}
}
