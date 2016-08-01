package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTo;

/**
 * Transport object to entity mapper for challenges
 * 
 * @author PPATRONI
 *
 */
public class ChallengeMapper {
	
	public static ChallengeTo map(ChallengeEntity challengeEntity) {
		if (challengeEntity != null) {
			ChallengeTo challengeTo = new ChallengeTo();
			challengeTo.setId(challengeEntity.getChallengeId());
			challengeTo.setFirstPlayerId(challengeEntity.getFirstPlayerId());
			challengeTo.setSecondPlayerId(challengeEntity.getSecondPlayerId());
			challengeTo.setStartDate(challengeEntity.getStartDate());
			challengeTo.setFinishDate(challengeEntity.getFinishDate());
			challengeTo.setStatus(challengeEntity.getStatus());
			return challengeTo;
		}
		return null;
	}

	public static ChallengeEntity map(ChallengeTo challengeTo) {
		if (challengeTo != null) {
			ChallengeEntity challengeEntity = new ChallengeEntity();
			challengeEntity.setChallengeId(challengeTo.getId());
			challengeEntity.setFirstPlayerId(challengeTo.getFirstPlayerId());
			challengeEntity.setSecondPlayerId(challengeTo.getSecondPlayerId());
			challengeEntity.setStartDate(challengeTo.getStartDate());
			challengeEntity.setFinishDate(challengeTo.getFinishDate());
			challengeEntity.setStatus(challengeTo.getStatus());
			return challengeEntity;
		}
		return null;
	}
	
	public static ChallengeEntity update(ChallengeEntity challengeEntity, ChallengeTo challengeTo) {
		if (challengeTo != null && challengeEntity != null) {
			challengeEntity.setChallengeId(challengeTo.getId());
			challengeEntity.setFirstPlayerId(challengeTo.getFirstPlayerId());
			challengeEntity.setSecondPlayerId(challengeTo.getSecondPlayerId());
			challengeEntity.setStartDate(challengeTo.getStartDate());
			challengeEntity.setFinishDate(challengeTo.getFinishDate());
			challengeEntity.setStatus(challengeTo.getStatus());
		}
		return challengeEntity;
	}
	
	public static List<ChallengeTo> map2TOs(List<ChallengeEntity> challengeEntities) {
		return challengeEntities.stream().map(ChallengeMapper::map).collect(Collectors.toList());
	}

	public static List<ChallengeEntity> map2Entities(List<ChallengeTo> challengeTos) {
		return challengeTos.stream().map(ChallengeMapper::map).collect(Collectors.toList());
	}
}
