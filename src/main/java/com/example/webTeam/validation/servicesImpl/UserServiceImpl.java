package com.example.webTeam.validation.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webTeam.validation.DTO.ChangePassword;
import com.example.webTeam.validation.DTO.EditRequest;
import com.example.webTeam.validation.models.User;
import com.example.webTeam.validation.repositories.UserRepository;
import com.example.webTeam.validation.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		// crypt password
		String encodedPassword = passwordEncoder.encode(user.getPwd());
		user.setPwd(encodedPassword);
		return userRepository.save(user);
	}
	
	@Override
	public User editUser(EditRequest editRequest) {
		Optional<User> userOptional = userRepository.findById(editRequest.getId());
		if (userOptional.isPresent()) {
			User userDB = userOptional.get();
			userDB.setFirstName(editRequest.getFirstName());
			userDB.setLastName(editRequest.getLastName());
			userDB.setPhone(editRequest.getPhone());
			userDB.setAddress(editRequest.getAddress());
			return userRepository.save(userDB);
		} else {
			throw new RuntimeException("User not found");
		}
	}

	@Override
	public User findUserById(Long id) {
		// return userRepository.findById(id).get();
		// Ou Bien
		Optional<User> user = userRepository.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	// @Override n'est pas obligatoire mais on doit garder la meme signature que
	// l'interface
	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public boolean changePassword(Long id, ChangePassword changePassword) {
		// Retrieve the user from the database
		Optional<User> optionalEntreprise = userRepository.findById(id);
		if (optionalEntreprise.isPresent()) {
			User user = optionalEntreprise.get();
			// Check if the old password matches the current password
			if (passwordEncoder.matches(changePassword.getOldPassword(), user.getPwd())) {
				// Encode the new password
				String encodedNewPassword = passwordEncoder.encode(changePassword.getNewPassword());
				user.setPwd(encodedNewPassword);
				// Save the updated user
				userRepository.save(user);
				return true; // Password change was successful
			} else {
				return false; // Old password does not match
			}
		} else {
			throw new EntityNotFoundException("User not found with ID: " + id);
		}
	}

	@Override
	public void unBlockUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setBlocked(false);
			userRepository.save(user);
		} else {
			throw new RuntimeException("User not found");
		}
	}

	@Override
	public void blockUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setBlocked(true);
			userRepository.save(user);
		} else {
			throw new RuntimeException("User not found");
		}
	}

}
