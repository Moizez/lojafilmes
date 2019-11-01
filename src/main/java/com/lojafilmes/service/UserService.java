package com.lojafilmes.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lojafilmes.model.Role;
import com.lojafilmes.model.User;
import com.lojafilmes.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleService roleService;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleService.findByRole("ADMIN");
        if(userRole == null) {
        	userRole = new Role();
        	userRole.setRole("ADMIN");
        	roleService.saveRole(userRole);
        	user.getRoles().add(userRole);
        }
        userRepository.save(user);
    }
 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.findByEmail(username);
		if(userDetails != null) {
			org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(userDetails.getUsername(), userDetails.getPassword(), getPermissoes(userDetails));
			return userFinal;
		}
		
		return null;
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		Set<Role> permissoesUsuario =  ((User) usuario).getRoles();
		for(Role r : permissoesUsuario ) {
			 System.out.println(r.getRole());
			 authorities.add(new SimpleGrantedAuthority(r.getRole().toUpperCase()));
		}
		return authorities;
	}
    
    

}
