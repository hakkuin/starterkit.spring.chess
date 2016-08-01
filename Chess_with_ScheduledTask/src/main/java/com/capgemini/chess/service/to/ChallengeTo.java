package com.capgemini.chess.service.to;

import java.util.Date;

import com.capgemini.chess.service.enums.ChallengeStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Challenge data storage.
 * 
 * @author Pawel Patronik
 *
 */
public class ChallengeTo extends IdAware {
	
	private Long challengeId = null;
	@Getter @Setter private long firstPlayerId;
	@Getter @Setter private long secondPlayerId;

	@Getter @Setter private Date startDate;
	@Getter @Setter private Date finishDate;
	@Getter @Setter private ChallengeStatus status;

	public ChallengeTo() {
	}

	@Override
	public Long getId() {
		return challengeId;
	}
	
	@Override
	public void setId(Long id) {
		this.challengeId = id;
	}
	
	public ChallengeTo(long firstPlayerId, long secondPlayerId, Date startDate) {
		this.firstPlayerId = firstPlayerId;
		this.secondPlayerId = secondPlayerId;
		this.startDate = startDate;
		this.finishDate = null;
		this.status = ChallengeStatus.PENDING;
	}
	
	public ChallengeTo(long id, Date startDate) {
		this.challengeId = new Long(id);
		this.startDate = startDate;
	}
}
