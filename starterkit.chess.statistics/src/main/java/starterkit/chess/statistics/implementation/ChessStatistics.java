package starterkit.chess.statistics.implementation;

import java.util.ArrayList;
import java.util.Collections;

import javax.naming.directory.InvalidAttributesException;

import lombok.Getter;
import starterkit.chess.statistics.comparators.ByPointsPlayerComparator;
import starterkit.chess.statistics.data.Player;

/**
 * Statistics utility for chess game.
 * Maintains player ranking with match update service and internal points calculation.
 * 
 * @author Pawel Patronik
 *
 */
public class ChessStatistics {
	@Getter private ArrayList<Player> ranking = new ArrayList<>();

	private static final int PROFIT_BASE = 40;
	private static final int LOSS_BASE = 30;

	public ChessStatistics() {
		
	}
	
	/**
	 * Adds new player to ranking.
	 * 
	 * @param player who is to be added to ranking
	 */
	public void addPlayerToRanking(Player player) {
		ranking.add(player);
	}
	
	/**
	 * Updates players' statistics after nondrawn match.
	 * 
	 * @param winner is a player, who won the match
	 * @param loser is a player, who lost the match
	 */
	public void updateRankingAfterMatch(Player winner, Player loser) {
		updatePlayerStatistics(winner, loser);
		
		sortRanking();
		
		updatePlayerNeighbourList(winner);
		updatePlayerNeighbourList(loser);
	}

	/**
	 * Updates players' statistics after drawn match.
	 * 
	 * @param player1
	 * @param player2
	 */
	public void updateRankingAfterDrawnMatch(Player player1, Player player2) {
		player1.updateWithDrawnGame();
		player2.updateWithDrawnGame();
		
		sortRanking();
		
		updatePlayerNeighbourList(player1);
		updatePlayerNeighbourList(player2);
	}

	/**
	 * Service for obtaining position in ranking for specific player.
	 * 
	 * @param player for which position should be returned
	 * @return Player position in ranking
	 * @throws InvalidAttributesException
	 */
	public int getPlayerPositionInRanking(Player player) throws InvalidAttributesException {
		if (ranking.contains(player)) {
			return ranking.indexOf(player) + 1;
		} else {
			throw new InvalidAttributesException("Player entry does not exist in ranking!");
		}
	}
	
	private void updatePlayerNeighbourList(Player player) {
		
		// TODO convert solution to utilize sublist

		ArrayList<Player> temporaryPlayerList = new ArrayList<>();
		if (ranking.size() - ranking.indexOf(player) - 1 >= 10) {
			for (int i = ranking.indexOf(player) + 1; i <= ranking.indexOf(player) + 10; i++) {
				temporaryPlayerList.add(ranking.get(i));
			}
		} else {
			for (int i = ranking.indexOf(player) + 1; i <= ranking.size() - 1; i++) {
				temporaryPlayerList.add(ranking.get(i));
			}
		}
		player.addPlayersAbove(temporaryPlayerList);
		temporaryPlayerList.clear();
		
		if (ranking.indexOf(player) + 1 >= 10) {
			for (int i = ranking.indexOf(player) - 10; i < ranking.indexOf(player); i++) {
				temporaryPlayerList.add(ranking.get(i));
			}
		} else {
			for (int i = 0; i < ranking.indexOf(player); i++) {
				temporaryPlayerList.add(ranking.get(i));
			}
		}
		player.addPlayersBelow(temporaryPlayerList);
	}

	private void updatePlayerStatistics(Player winner, Player loser) {
		winner.updateWithWonGame(calculateProfit(winner, loser));
		loser.updateWithLostGame(calculateLoss(winner, loser));
	}

	private void sortRanking() {
		Collections.sort(ranking, new ByPointsPlayerComparator());
	}

	private int calculateBaseProfit(Player winner, Player loser) {
		return (int) Math.max(PROFIT_BASE * Math.pow(2, loser.getLevel().getValue() - winner.getLevel().getValue()), 1) * winner.getLevel().getValue();
	}

	private int calculateBaseLoss(Player winner, Player loser) {
		return (int) Math.max(LOSS_BASE * Math.pow(2, loser.getLevel().getValue() - winner.getLevel().getValue()), 1) * loser.getLevel().getValue();
	}

	private int calculateProfitBonus(Player winner, Player loser) {
		return (int) Math.floor(loser.getProgress() - winner.getProgress() * calculateBaseProfit(winner, loser) * 0.5);
	}

	private int calculateLossBonus(Player winner, Player loser) {
		return (int) Math.floor(winner.getProgress() - loser.getProgress() * calculateBaseLoss(winner, loser) * 0.5);
	}

	private int calculateProfit(Player winner, Player loser) {
		return calculateBaseProfit(winner, loser) + calculateProfitBonus(winner, loser);
	}

	private int calculateLoss(Player winner, Player loser) {
		return calculateBaseProfit(winner, loser) - calculateLossBonus(winner, loser);
	}
}
