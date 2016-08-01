package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.StatisticsDao;
import com.capgemini.chess.service.StatisticsService;
import com.capgemini.chess.service.to.StatisticsTo;

import lombok.Getter;
import lombok.Setter;

/**
 * Statistics management and life cycle coordination class
 * 
 * @author Pawel Patronik
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	@Getter @Setter StatisticsDao statisticsDao;
	
	public StatisticsTo readUserStatistics(Long userId) {
		return statisticsDao.findStatisticsByUserId(userId);
	}
	
	public List<StatisticsTo> readAllStatistics() {
		return statisticsDao.findAllStatistics();
	}
}
