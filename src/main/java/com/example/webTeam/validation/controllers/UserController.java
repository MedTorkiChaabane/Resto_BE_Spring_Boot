package com.example.webTeam.validation.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webTeam.validation.DTO.EditRequest;
import com.example.webTeam.validation.DTO.LoginRequest;
import com.example.webTeam.validation.DTO.SignupRequest;
import com.example.webTeam.validation.auth.JwtUtil;
import com.example.webTeam.validation.models.Role;
import com.example.webTeam.validation.models.User;
import com.example.webTeam.validation.repositories.RoleRepository;
import com.example.webTeam.validation.services.UserService;
import com.example.webTeam.validation.servicesImpl.UserServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	private final JwtUtil jwtUtil = new JwtUtil();

	@GetMapping()
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/signup")
	public User addUser(@RequestBody SignupRequest signupRequest) throws IOException {

		User user = new User();
		user.setFirstName(signupRequest.getFirstName());
		user.setLastName(signupRequest.getLastName());
		user.setEmail(signupRequest.getEmail());
		user.setPwd(signupRequest.getPwd());
		user.setPhone(signupRequest.getPhone());
		user.setAddress(signupRequest.getAddress());
		user.setSpeciality(signupRequest.getSpeciality());
		user.setExperience(signupRequest.getExperience());
		user.setBlocked(signupRequest.isBlocked());
		
		// Charger les rôles
		List<Role> roles = new ArrayList<>();
		for (String roleName : signupRequest.getRoles()) {
			Role role = roleRepository.findByName(roleName)
					.orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
			roles.add(role);
		}

		user.setRoles(roles);

		return userService.addUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
		Map<String, Object> map = new HashMap<>();

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPwd()));

			if (authentication.isAuthenticated()) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
				User user = userServiceImpl.getUserByEmail(loginRequest.getEmail());
				String token = jwtUtil.createToken1(userDetails, user);
				map.put("status", HttpStatus.OK.value());
				map.put("message", "Authentication successful");
				map.put("token", token);
				return ResponseEntity.ok(map);
			} else {
				map.put("status", HttpStatus.UNAUTHORIZED.value());
				map.put("message", "Authentication failed");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
			}
		} catch (BadCredentialsException ex) {
			map.put("status", HttpStatus.UNAUTHORIZED.value());
			map.put("message", "Bad credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
		} catch (LockedException ex) {
			map.put("status", HttpStatus.UNAUTHORIZED.value());
			map.put("message", "Your account is locked");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
		} catch (DisabledException ex) {
			map.put("status", HttpStatus.UNAUTHORIZED.value());
			map.put("message", "Your account is disabled");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
		} catch (AuthenticationException ex) {
			map.put("status", HttpStatus.UNAUTHORIZED.value());
			map.put("message", "Authentication failed");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
		}
	}
	
	@PutMapping("/unblock/{id}")
	public void unBlockUser(@PathVariable Long id) {
		
		userService.unBlockUser(id);
	}

	@PutMapping("block/{id}")
	public void blockUser(@PathVariable Long id) {
		
		userService.blockUser(id);
	}
	
	@PutMapping("user-edit")
	public User editUser(@RequestBody EditRequest editRequest) {
		return userService.editUser(editRequest);
		
	}
}
