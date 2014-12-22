package com.e6893.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Page MVC Controller
 * 
 * @author Jairo Pava
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/home")
	public String sayWelcome(Model model) {
		model.addAttribute("welcome", "Welcome to the Student Tracker App!");
		return "home";
	}
	
}
