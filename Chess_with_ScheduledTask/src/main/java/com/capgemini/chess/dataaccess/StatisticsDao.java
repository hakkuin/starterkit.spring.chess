package com.capgemini.chess.dataaccess;

import java.util.List;

import com.capgemini.chess.service.to.StatisticsTo;

/**
 * Statistics data access object interface
 * 
 * @author PPATRONI
 *
 */
public interface StatisticsDao {

	public List<StatisticsTo> findAllStatistics();
    
    public StatisticsTo findStatisticsByUserId(long id);

    public StatisticsTo updateUserStatistics(StatisticsTo updatedStatistics);
}
