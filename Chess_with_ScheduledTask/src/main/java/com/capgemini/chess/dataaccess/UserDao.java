package com.capgemini.chess.dataaccess;

import java.util.Collection;

import com.capgemini.chess.service.to.IdAware;
import com.capgemini.chess.service.to.StatisticsTo;
import com.capgemini.chess.service.to.UserProfileTo;

/**
 * User profile data access object interface
 * 
 * @author PPATRONI
 *
 */
public interface UserDao {

	public Collection<StatisticsTo> findAllUsers();
    
    public UserProfileTo findUserById(long id);

    public UserProfileTo updateUserProfile(UserProfileTo updatedProfile);
    
    public IdAware createNewUser(IdAware idAware);

    public void delete(long id);
    
    public Long getNextId();
}
