package com.capgemini.chess.dataaccess.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.IdAware;
import com.capgemini.chess.service.to.StatisticsTo;
import com.capgemini.chess.service.to.UserProfileTo;

/**
 * Implementation of user profile data access object
 * 
 * @author PPATRONI
 *
 */
@Service
public class UserDaoImpl implements UserDao {

	static Long userIds = 0L;
	
	public Collection<StatisticsTo> findAllUsers() {
		return null;
	}
    
    public UserProfileTo findUserById(long id) {
    	return new UserProfileTo("", "", "");
    }

    public UserProfileTo updateUserProfile(UserProfileTo updatedProfile) {
    	return updatedProfile;
    }
    
    public IdAware createNewUser(IdAware idAware) {
    	return idAware;
    }

    public void delete(long id) {
    	
    }
    
    public Long getNextId() {
    	return ++userIds;
    }
}
