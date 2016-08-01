package com.capgemini.chess.dataaccess.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.StatisticsDao;
import com.capgemini.chess.service.to.StatisticsTo;

/**
 * Implementation of statistics data access object
 * 
 * @author PPATRONI
 *
 */
@Service
public class StatisticsDaoImpl implements StatisticsDao {

	public List<StatisticsTo> findAllStatistics() {
		return null;
	}
    
    public StatisticsTo findStatisticsByUserId(long id) {
    	return null;
    }

    public StatisticsTo updateUserStatistics(StatisticsTo updatedStatistics) {
    	return null;
    }
}
