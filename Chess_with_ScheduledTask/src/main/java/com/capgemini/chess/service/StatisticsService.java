package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.StatisticsTo;

/**
 * Interface for satistics service utility
 * 
 * @author PPATRONI
 *
 */
public interface StatisticsService {

	public StatisticsTo readUserStatistics(Long userId);

	public List<StatisticsTo> readAllStatistics();
}
