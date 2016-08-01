package com.capgemini.chess.service.to;

import lombok.Getter;
import lombok.Setter;
import starterkit.chess.statistics.data.Player;


/**
 * User statistics in chess ranking data storage.
 * 
 * @author Pawel Patronik
 *
 */
public class StatisticsTo extends IdAware {
	
	private Long userId;
	@Getter @Setter private Player playerStats = new Player();
	
	@Override
	public Long getId() {
		return userId;
	}
	
	@Override
	public void setId(Long id) {
		this.userId = id;
	}
	
	public StatisticsTo() {
	}
	
	public StatisticsTo(long id) {
		this.userId = new Long(id);
	}
}
