package com.capgemini.chess.service;

import com.capgemini.chess.service.to.UserProfileTo;

/**
 * Interface for user profile service utility
 * 
 * @author PPATRONI
 *
 */
public interface UserService {
	
	public UserProfileTo readUser(Long id);

	public UserProfileTo createUser(String login, String password, String email);
}
