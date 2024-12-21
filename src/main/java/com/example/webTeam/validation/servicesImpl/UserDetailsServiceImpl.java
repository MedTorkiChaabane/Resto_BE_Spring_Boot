package com.example.webTeam.validation.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.webTeam.validation.models.User;
import com.example.webTeam.validation.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// il faut utiliser une methode qui retourne un objet selon un attribut unique
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return org.springframework.security.core.userdetails.User.withUsername(email).password(user.getPwd()).build();
	}
}
