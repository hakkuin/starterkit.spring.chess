package starterkit.chess.statistics.comparators;

import java.util.Comparator;

import starterkit.chess.statistics.data.Player;

/**
 * Comparator class for comparison of two players based on point number.
 * 
 * @author Pawel Patronik
 *
 */
public class ByPointsPlayerComparator implements Comparator<Player>{
	@Override
	public int compare(Player p1, Player p2) {
		return new Integer(p2.getPoints()).compareTo(new Integer(p1.getPoints()));
	}
}
