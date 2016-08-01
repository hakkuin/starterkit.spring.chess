package com.capgemini.chess.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.capgemini.chess.dataaccess.ChallengeDao;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.IdAware;

/**
 * Advisor for generating new numbers for newly created entities
 * 
 * @author PPATRONI
 *
 */
@Aspect
public class IdNumberingAdvisor {

	@Before("execution(* com.capgemini.chess.dataaccess.ChallengeDao.save(..)) && args(idAware)")
	public void setNewChallengeId(JoinPoint joinPoint, IdAware idAware) {
		ChallengeDao o = (ChallengeDao) joinPoint.getThis();
		idAware.setId(o.getNextId());
	}
	
	@Before("execution(* com.capgemini.chess.dataaccess.UserDao.createNewUser(..)) && args(idAware))")
	public void setNewUserId(JoinPoint joinPoint, IdAware idAware) {
		UserDao o = (UserDao) joinPoint.getThis();
		idAware.setId(o.getNextId());
	}
}
