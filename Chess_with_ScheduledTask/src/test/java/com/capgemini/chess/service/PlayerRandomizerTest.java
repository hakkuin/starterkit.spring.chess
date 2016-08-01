package com.capgemini.chess.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.StatisticsDao;
import com.capgemini.chess.dataaccess.impl.StatisticsDaoImpl;
import com.capgemini.chess.service.impl.PlayerRandomizer;
import com.capgemini.chess.service.to.StatisticsTo;

import starterkit.chess.statistics.data.Player;
import starterkit.chess.statistics.enums.Level;

/**
 * Test class for testing {@link PlayerRandomizer}
 * 
 * @author PPATRONI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
public class PlayerRandomizerTest {
	@Autowired
	PlayerRandomizer randomizer;

	StatisticsDao statisticsDao = mock(StatisticsDaoImpl.class);
	List<StatisticsTo> allStatistics = new ArrayList<>();
	
	@Before
	public void testInit() {
		Whitebox.setInternalState(randomizer.getStatisticsService(), "statisticsDao", statisticsDao);
	}
	
	@Test
	public void shouldPickOneUser() throws Exception {
		// given
		StatisticsTo userStatistics = new StatisticsTo(1L);
		userStatistics.setPlayerStats(mock(Player.class));
		allStatistics.add(userStatistics);
		
		// when
		when(userStatistics.getPlayerStats().getLevel()).thenReturn(Level.BEGINNER);
		when(statisticsDao.findAllStatistics()).thenReturn(allStatistics);
		List<Long> pickedUsers = randomizer.drawFiveRandomPlayersAccordingToPlayerLevel(Level.BEGINNER);
		
		// then
		assertNotNull(pickedUsers);
		assertTrue(pickedUsers.contains(new Long(1)));
	}
			
	@Test
	public void shouldPickFiveUsers() throws Exception {
		// given
		for (int i = 0; i < 5; i++) {
			allStatistics.add(new StatisticsTo(new Long(i+1)));
			allStatistics.get(i).setPlayerStats(mock(Player.class));
		}
		
		// when
		for (int i = 0; i < 5; i++) {
			when(allStatistics.get(i).getPlayerStats().getLevel()).thenReturn(Level.getLevelByValue(i+1));
		}
		when(statisticsDao.findAllStatistics()).thenReturn(allStatistics);
		List<Long> pickedUsers = randomizer.drawFiveRandomPlayersAccordingToPlayerLevel(Level.BEGINNER);
		
		// then
		assertTrue(pickedUsers.contains(1L));
		assertTrue(pickedUsers.contains(5L));
		assertEquals(5, pickedUsers.size());
	}
	
	@Test
	public void shouldPickFourUsers() throws Exception {
		// given
		for (int i = 0; i < 6; i++) {
			allStatistics.add(new StatisticsTo(new Long(i+1)));
			allStatistics.get(i).setPlayerStats(mock(Player.class));
		}
		
		// when
		for (int i = 0; i < 6; i++) {
			when(allStatistics.get(i).getPlayerStats().getLevel()).thenReturn(Level.getLevelByValue(i+2));
		}
		when(statisticsDao.findAllStatistics()).thenReturn(allStatistics);
		List<Long> pickedUsers = randomizer.drawFiveRandomPlayersAccordingToPlayerLevel(Level.BEGINNER);
		
		// then
		assertTrue(pickedUsers.contains(4L));
		assertFalse(pickedUsers.contains(5L));
		assertEquals(4, pickedUsers.size());
	}
	
	@Test
	public void shouldPickThreeUsers() throws Exception {
		// given
		for (int i = 0; i < 5; i++) {
			allStatistics.add(new StatisticsTo(new Long(i+1)));
			allStatistics.get(i).setPlayerStats(mock(Player.class));
		}
		
		// when
		for (int i = 0; i < 5; i++) {
			when(allStatistics.get(i).getPlayerStats().getLevel()).thenReturn(Level.getLevelByValue(i+1));
		}
		when(statisticsDao.findAllStatistics()).thenReturn(allStatistics);
		List<Long> pickedUsers = randomizer.drawFiveRandomPlayersAccordingToPlayerLevel(Level.MIDDLEBROW);
		
		// then
		assertTrue(pickedUsers.contains(3L));
		assertFalse(pickedUsers.contains(2L));
		assertEquals(3, pickedUsers.size());
	}
}
