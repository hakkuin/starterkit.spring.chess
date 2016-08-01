package com.capgemini.chess.service.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import starterkit.chess.statistics.data.Player;

/**
 * User profile data container.
 * 
 * @author Pawel Patronik
 *
 */
public class UserProfileTo extends IdAware{
	
	@Getter @Setter private Long id = null;
	@Getter @Setter private String login;
	private String password;
	@Getter @Setter private String name;
	@Getter @Setter private String surname;
	@Getter @Setter private String email;
	@Getter @Setter private String aboutMe;
	@Getter @Setter private String lifeMotto;
	
	@Getter @Setter private Player playerStats = new Player();
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserProfileTo() {
	}

	public UserProfileTo(String login, String password, String email) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
	}
}
