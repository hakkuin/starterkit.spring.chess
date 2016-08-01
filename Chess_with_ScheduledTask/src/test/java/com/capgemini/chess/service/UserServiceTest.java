package com.capgemini.chess.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.service.to.UserProfileTo;

/**
 * Test class for testing {@link UserService}
 * 
 * @author PPATRONI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
public class UserServiceTest {

	@Autowired
	UserService service;
	
	@Test
	public void testReadUserSuccessful() throws Exception {
		// when
		UserProfileTo userTo = service.readUser(1L);
		assertNotNull(userTo);
	}
	
	@Test
	public void testShouldGenerateNextId() throws Exception {
		// when
		UserProfileTo userTo = service.createUser("chessMaster", "123456", "Marian28cm@wp.pl");
		
		// then
		assertNotNull(userTo.getId());
	}
}
