package com.lojafilmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//Abre a página home
	@RequestMapping("/")
	public String index() {
		return "/index";
	}
	
	//Abre a página login
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	
	//Login inválido
	@RequestMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("titulo", "Credenciais inválidas!");
		model.addAttribute("texto", "E-mail ou senha incorretos, tente novamente!");
		return "/login";
	}
	
}
	
