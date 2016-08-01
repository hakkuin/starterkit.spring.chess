package com.capgemini.chess.dataaccess.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.ChallengeDao;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.IdAware;

/**
 * Implementation of challenge data access object
 * 
 * @author PPATRONI
 *
 */
@Service
public class ChallengeDaoImpl implements ChallengeDao {

	static Long challengeIds = new Long(0L);
	
	public Collection<ChallengeTo> findAll() {
		return null;
	}

	public ChallengeTo findChallengeById(long id) {
		return null;
	}

	public IdAware save(IdAware idAware) {
		return idAware;
	}

	public void delete(long id) {
		
	}

	public void deleteAll(List<Long> ids) {
		
	}
	
	public Long getNextId() {
		return ++challengeIds;
	}
}
