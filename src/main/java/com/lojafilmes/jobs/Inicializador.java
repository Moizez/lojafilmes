package com.lojafilmes.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.lojafilmes.model.Role;
import com.lojafilmes.model.User;
import com.lojafilmes.service.RoleService;
import com.lojafilmes.service.UserService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Role role = roleService.findByRole("ADMIN"); 
		if(role == null) {
			User user = new User();
			user.setEmail("moises@admin.com");
			user.setNome("Moises");
			user.setPassword("123");
			user.setSobrenome("Henrique");
			userService.saveUser(user);
		}
	}

}
