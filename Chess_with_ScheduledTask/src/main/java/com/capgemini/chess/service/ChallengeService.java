package com.capgemini.chess.service;

import java.util.Collection;
import java.util.List;

import com.capgemini.chess.service.to.ChallengeTo;

/**
 * Interface for challenge service utility
 * 
 * @author PPATRONI
 *
 */
public interface ChallengeService {

	public ChallengeTo createChallenge(long firstPlayerId, long secondPlayerId);

	public ChallengeTo readChallenge(long id);

	public Collection<ChallengeTo> readAllChallenges();
	
	public List<ChallengeTo> readAllChallengesOlderThan(int timeInMs);

	public void deleteChallenge(long id);

	public void deleteAllChallenges(List<Long> ids);
}