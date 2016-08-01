package starterkit.chess.statistics.data;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import starterkit.chess.statistics.enums.Level;

/**
 * Information on player's stats.
 * 
 * @author Michal Bejm
 *
 */
public class Player {

	@Getter private Level level;
	@Getter @Setter private int points;
	@Getter @Setter private int gamesPlayed;
	@Getter @Setter private int gamesWon;
	@Getter @Setter private int gamesDrawn;
	@Getter @Setter private int gamesLost;

	@Getter private ArrayList<Player> playersAbove;
	@Getter private ArrayList<Player> playersBelow;

	public Player(int points, int gamesWon, int gamesDrawn, int gamesLost) {
		this.points = points;
		this.gamesWon = gamesWon;
		this.gamesDrawn = gamesDrawn;
		this.gamesLost = gamesLost;
		this.gamesPlayed = gamesWon + gamesLost + gamesDrawn;
		this.playersAbove = new ArrayList<>();
		this.playersBelow = new ArrayList<>();
		this.level = Level.NEWBIE;
		updateLevel();
	}

	public Player() {
		this.points = 0;
		this.gamesWon = 0;
		this.gamesDrawn = 0;
		this.gamesLost = 0;
		this.gamesPlayed = 0;
		this.playersAbove = new ArrayList<>();
		this.playersBelow = new ArrayList<>();
		this.level = Level.NEWBIE;
	}

	public void addPlayersAbove(ArrayList<Player> playersAbove) {
		this.playersAbove.clear();
		this.playersAbove.addAll(playersAbove);
	}

	public void addPlayersBelow(ArrayList<Player> playersBelow) {
		this.playersBelow.clear();
		this.playersBelow.addAll(playersBelow);
	}

	public void updateWithLostGame(int points) {

		this.gamesLost++;
		this.gamesPlayed++;

		this.points -= points;

		if (this.points < 0) {
			this.points = 0;
		}

		updateLevel();
	}

	public void updateWithWonGame(int points) {

		this.gamesWon++;
		this.gamesPlayed++;

		this.points += points;

		updateLevel();
	}

	public void updateWithDrawnGame() {
		
		this.gamesDrawn++;
		this.gamesPlayed++;
		
		updateLevel();
	}

	public void updateLevel() {

		if (level != Level.CHUCK_NORRIS_OF_CHESS) {
			while (getProgress() == 1) {
				promoteToNextLevel();
			}
		}

		if (level != Level.NEWBIE) {
			while (getPointsProgress() < 0 || getGamesProgress() < 0 || getWinsProgress() < 0) {
				degradeToPreviousLevel();
			}
		}
	}

	public int getPertencageOfWins() {
		if (gamesPlayed != 0) {
			return 100 * gamesWon / gamesPlayed;
		} else {
			return 0;
		}
	}

	public double getPointsProgress() {
		if (level == Level.CHUCK_NORRIS_OF_CHESS) {
			return Math.min((double) points / (double) level.getPointsRequired() - (double) 1, 0);
		}
		return Math.min(((double) points - (double) level.getPointsRequired())
				/ ((double) level.getNext().getPointsRequired() - (double) level.getPointsRequired()), 1);
	}

	public double getGamesProgress() {
		if (level == Level.CHUCK_NORRIS_OF_CHESS) {
			return Math.min((double) gamesPlayed / (double) level.getGamesRequired() - (double) 1, 0);
		}
		return Math.min(((double) gamesPlayed - (double) level.getGamesRequired())
				/ ((double) level.getNext().getGamesRequired() - (double) level.getGamesRequired()), 1);
	}

	public double getWinsProgress() {
		if (level == Level.CHUCK_NORRIS_OF_CHESS) {
			return Math.min(getPertencageOfWins() / (double) level.getWinsRequired() - (double) 1, 0);
		}
		return Math.min((getPertencageOfWins() - 100 * (double) level.getWinsRequired())
				/ (100 * (double) level.getNext().getWinsRequired() - 100 * (double) level.getWinsRequired()), 1);
	}

	public double getProgress() {
		return (getPointsProgress() + getGamesProgress() + getWinsProgress()) / 3;
	}

	public void promoteToNextLevel() {
		level = level.getNext();
}

	public void degradeToPreviousLevel() {
		level = level.getPrevious();
	}

}
