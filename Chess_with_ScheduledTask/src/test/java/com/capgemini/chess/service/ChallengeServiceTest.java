package com.capgemini.chess.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.ChallengeDao;
import com.capgemini.chess.dataaccess.impl.ChallengeDaoImpl;
import com.capgemini.chess.service.to.ChallengeTo;

/**
 * Test class for testing {@link ChallengeService}
 * 
 * @author PPATRONI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
public class ChallengeServiceTest {

	@Autowired
	ChallengeService service;

	ChallengeDao challengeDao = Mockito.mock(ChallengeDaoImpl.class);


	@Test
	public void testShouldSaveChallenge() throws Exception {
		// given

		// when
		ChallengeTo result = service.createChallenge(1L, 2L);

		// then
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(1L, result.getFirstPlayerId());
		assertEquals(2L, result.getSecondPlayerId());
	}

	@Test
	public void testShouldReadChallenge() throws Exception {
		// given
		Whitebox.setInternalState(service, "challengeDao", challengeDao);
		ChallengeTo challenge = new ChallengeTo(1L, 2L, new Date());
		challenge.setId(69L);

		// when
		Mockito.when(challengeDao.findChallengeById(69L)).thenReturn(challenge);
		ChallengeTo result = service.readChallenge(69L);

		// then
		assertNotNull(result);
		assertEquals(new Long(69), result.getId());
	}

	@Test
	public void testShouldReadAllChallenges() throws Exception {
		// given
		Collection<ChallengeTo> challenges = new ArrayList<>();
		challenges.add(new ChallengeTo(6, 7, new Date()));
		challenges.add(new ChallengeTo(5, 8, new Date()));
		Whitebox.setInternalState(service, "challengeDao", challengeDao);
		
		// when
		Mockito.when(challengeDao.findAll()).thenReturn(challenges);
		Collection<ChallengeTo> result = service.readAllChallenges();
		
		// then
		assertNotNull(result);
		Mockito.verify(challengeDao).findAll();
		assertEquals(2, result.size());
	}
}
