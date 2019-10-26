package com.lojafilmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lojafilmes.model.Usuario;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@RequestMapping("/")
	public ModelAndView admin(Usuario usuario) {
		ModelAndView mv = new ModelAndView("administrador/home");
		mv.addObject("usuario", usuario);
		return mv;
	}

}
