package com.capgemini.chess.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.ChallengeDao;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTo;

import lombok.Getter;
import lombok.Setter;

/**
 * Challenge management and life cycle coordination class
 * 
 * @author Pawel Patronik
 *
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {
	
	@Autowired
	@Getter @Setter ChallengeDao challengeDao;
	
	@Override
	public ChallengeTo createChallenge(long firstPlayerId, long secondPlayerId) {
		return (ChallengeTo) challengeDao.save(new ChallengeTo(firstPlayerId, secondPlayerId, new Date()));
	}

	@Override
	public List<ChallengeTo> readAllChallengesOlderThan(int timeInMs) {
		return challengeDao.findAll()
				.stream()
				.filter(p -> isOlderThan(p, timeInMs))
				.collect(Collectors.toList());
	}

	private boolean isOlderThan(ChallengeTo p, int timeInMs) {
		return new Date().getTime() - p.getStartDate().getTime() > timeInMs;
	}
	
	@Override
	public ChallengeTo readChallenge(long id) {
		return challengeDao.findChallengeById(id);
	}

	@Override
	public Collection<ChallengeTo> readAllChallenges() {
		return challengeDao.findAll();
	}

	@Override
	public void deleteChallenge(long id) {
		challengeDao.delete(id);
	}

	@Override
	public void deleteAllChallenges(List<Long> ids) {
		for (Long id : ids) {
			challengeDao.delete(id);
		}
	}
}
