package com.capgemini.chess.batch;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.ChallengeDao;
import com.capgemini.chess.dataaccess.impl.ChallengeDaoImpl;
import com.capgemini.chess.service.to.ChallengeTo;


/**
 * Test class for testing {@link ScheduledChallengeClean}
 * 
 * @author PPATRONI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
public class ScheduledChallengeCleanUpTest {

	@Autowired
	private ScheduledChallengeClean scheduledChallengeClean;

	ChallengeDao challengeDao = Mockito.mock(ChallengeDaoImpl.class);

	@Test
	public void shouldInitiateCleaner() {
		// then
		assertNotNull(scheduledChallengeClean);
	}

	@Test
	public void shouldFilterOutdatedChallenges() {
		// given
		Whitebox.setInternalState(scheduledChallengeClean.getChallengeService(), "challengeDao", challengeDao);
		ArrayList<ChallengeTo> challenges = new ArrayList<>();
		challenges.add(new ChallengeTo(1L, new Date(1)));
		challenges.add(new ChallengeTo(2L, new Date(2)));
		challenges.add(new ChallengeTo(3L, new Date()));
		
		// when
		Mockito.when(challengeDao.findAll()).thenReturn(challenges);
		scheduledChallengeClean.removeOutdatedChallenges();
		
		// then
		Mockito.verify(challengeDao).findAll();
		Mockito.verify(challengeDao).delete(1L);
		Mockito.verify(challengeDao).delete(2L);
		Mockito.verify(challengeDao, never()).delete(3L);
	}
}
