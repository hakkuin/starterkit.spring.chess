package com.capgemini.chess.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.StatisticsService;
import com.capgemini.chess.service.to.StatisticsTo;

import lombok.Getter;
import lombok.Setter;
import starterkit.chess.statistics.enums.Level;

/**
 * Module for random picking of proposed player list
 * 
 * @author Pawel Patronik
 *
 */
@Service
public class PlayerRandomizer {

	@Autowired
	@Getter @Setter StatisticsService statisticsService; 
	
	public List<Long> drawFiveRandomPlayersAccordingToPlayerLevel(Level level) {
		List<StatisticsTo> validStatistics = new ArrayList<>();

		validStatistics.addAll(statisticsService.readAllStatistics()
				.stream()
				.filter(p -> isLevelDifferenceLowerThanTwo(level, p))
				.collect(Collectors.toList()));

		return drawFiveElementsAtRandom(validStatistics);
	}

	private List<Long> drawFiveElementsAtRandom(List<StatisticsTo> validStatistics) {
		List<Long> randomPlayers = new ArrayList<>();
		
		Collections.shuffle(validStatistics);
		
		int drawnPlayerNumber = 5;
		if (validStatistics.size() < 5) {
			drawnPlayerNumber = validStatistics.size();
		}
		
		for (int i = 0; i < drawnPlayerNumber; i++) {
			randomPlayers.add(validStatistics.get(i).getId());
		}
		
		return randomPlayers;
	}

	private boolean isLevelDifferenceLowerThanTwo(Level level, StatisticsTo p) {
		return Math.abs(level.getValue() - p.getPlayerStats().getLevel().getValue()) <= 2;
	}
}
