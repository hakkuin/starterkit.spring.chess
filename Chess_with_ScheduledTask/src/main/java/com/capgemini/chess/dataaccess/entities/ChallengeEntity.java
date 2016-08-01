package com.capgemini.chess.dataaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.capgemini.chess.service.enums.ChallengeStatus;

/**
 * Challenge entity
 * 
 * @author PPATRONI
 *
 */
@Entity
public class ChallengeEntity {

	@Id
	@GeneratedValue
	private long challengeId;
	@Column(nullable = false)
	private long firstPlayerId;
	@Column(nullable = false)
	private long secondPlayerId;

	@Column(nullable = false)
	private Date startDate;
	private Date finishDate;
	@Column(nullable = false)
	private ChallengeStatus status;

	public long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public long getFirstPlayerId() {
		return firstPlayerId;
	}

	public void setFirstPlayerId(long firstPlayerId) {
		this.firstPlayerId = firstPlayerId;
	}

	public long getSecondPlayerId() {
		return secondPlayerId;
	}

	public void setSecondPlayerId(long secondPlayerId) {
		this.secondPlayerId = secondPlayerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public ChallengeStatus getStatus() {
		return status;
	}

	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}
}
