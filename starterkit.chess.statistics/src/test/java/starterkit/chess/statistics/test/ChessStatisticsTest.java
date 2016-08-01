package starterkit.chess.statistics.test;

import static org.junit.Assert.*;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Test;

import starterkit.chess.statistics.data.Player;
import starterkit.chess.statistics.implementation.ChessStatistics;


/**
 * Test class for testing {@link ChessStatistics}
 * 
 * @author Pawel Patronik
 *
 */
public class ChessStatisticsTest {

	@Test
	public void shouldPromotePlayerToNextLevelAfterWonMatch() {
		// given
		Player hero = new Player(1200, 20, 0, 25);
		Player villain = new Player();
		ChessStatistics statisticsManager = new ChessStatistics();
		statisticsManager.addPlayerToRanking(hero);
		statisticsManager.addPlayerToRanking(villain);
		
		// when
		statisticsManager.updateRankingAfterMatch(hero, villain);
		
		// then
		assertEquals(4, hero.getLevel().getValue());
		assertEquals(46, hero.getGamesPlayed());
		assertEquals(21, hero.getGamesWon());
		assertEquals(45, hero.getPertencageOfWins());
		assertEquals(1215, hero.getPoints());
	}
	
	@Test
	public void shouldNotPromotePlayerToNextLevelAfterWonMatchWhenMeetsOnlyTwoCriteria() {
		// given
		Player hero = new Player(800, 20, 0, 25);
		Player villain = new Player();
		ChessStatistics statisticsManager = new ChessStatistics();
		statisticsManager.addPlayerToRanking(hero);
		statisticsManager.addPlayerToRanking(villain);
		
		// when
		statisticsManager.updateRankingAfterMatch(hero, villain);
		
		// then
		assertEquals(3, hero.getLevel().getValue());
		assertEquals(818, hero.getPoints());
	}
	
	@Test
	public void shouldPromotePlayerThreeLevelsUp() {
		// given
		Player hero = new Player(300, 98, 0, 286);
		Player villain = new Player(76801,500,0,0);
		ChessStatistics statisticsManager = new ChessStatistics();
		statisticsManager.addPlayerToRanking(hero);
		statisticsManager.addPlayerToRanking(villain);
		
		// when
		statisticsManager.updateRankingAfterMatch(hero, villain);
		
		// then
		assertEquals(10551, hero.getPoints());
		assertEquals(25, hero.getPertencageOfWins());
		assertEquals(4, hero.getLevel().getValue());
		assertEquals(9, villain.getLevel().getValue());
	}
	
	@Test
	public void shouldUpdateDrawnGame() {
		// given
		Player hero = new Player(1, 0, 0, 0);
		Player villain = new Player(76801,500,0,0);
		ChessStatistics statisticsManager = new ChessStatistics();
		statisticsManager.addPlayerToRanking(hero);
		statisticsManager.addPlayerToRanking(villain);
		for (int i = 0; i < 30; i++) {
			statisticsManager.addPlayerToRanking(new Player(10+i, 2+i, 0, 42-i));
		}
		
		// when
		statisticsManager.updateRankingAfterDrawnMatch(hero, villain);
		
		// then
		assertEquals(1, hero.getPoints());
		assertEquals(76801, villain.getPoints());
		assertTrue(statisticsManager.getRanking().get(0).getPoints() > statisticsManager.getRanking().get(1).getPoints());
		assertTrue(statisticsManager.getRanking().get(30).getPoints() > statisticsManager.getRanking().get(31).getPoints());
	}

	@Test(expected = InvalidAttributesException.class)
	public void shouldThrowExceptionWhenPlayerDoesNotAddedToRanking() throws InvalidAttributesException {
		// given
		Player player = new Player();
		ChessStatistics statistics = new ChessStatistics();
		
		// when
		statistics.getPlayerPositionInRanking(player);
	}

	@Test
	public void shouldReturnReturnCorrectPlayerPositionInRanking() throws InvalidAttributesException {
		// given
		Player moderatePlayer = new Player(1000, 20, 0, 20);
		Player beginner = new Player();
		ChessStatistics statisticsManager = new ChessStatistics();
		statisticsManager.addPlayerToRanking(moderatePlayer);
		statisticsManager.addPlayerToRanking(new Player(2000,100,0,0));
		statisticsManager.addPlayerToRanking(beginner);
		
		// when
		statisticsManager.updateRankingAfterDrawnMatch(moderatePlayer, beginner);
		
		// then
		assertEquals(2, statisticsManager.getPlayerPositionInRanking(moderatePlayer));
		assertEquals(3, statisticsManager.getPlayerPositionInRanking(beginner));
	}
}
