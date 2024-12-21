package com.example.webTeam.validation.services;

import java.util.List;

import com.example.webTeam.validation.DTO.ChangePassword;
import com.example.webTeam.validation.DTO.EditRequest;
import com.example.webTeam.validation.models.User;

public interface UserService {
	 public User addUser(User user);
	 public List<User> getAllUsers();
	 public User findUserById(Long id);
	 public User editUser(EditRequest editRequest);
	 public void deleteUser(Long id);
	 public User getUserByEmail(String email);
	 public boolean changePassword(Long id, ChangePassword changePassword);
	 public void unBlockUser(Long id);
	 public void blockUser(Long id);
}
