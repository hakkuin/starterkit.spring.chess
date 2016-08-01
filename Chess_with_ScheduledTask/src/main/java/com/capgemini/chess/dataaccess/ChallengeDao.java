package com.capgemini.chess.dataaccess;

import java.util.Collection;

import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.IdAware;

/**
 * Challenge data access object interface
 * 
 * @author PPATRONI
 *
 */
public interface ChallengeDao {

    Collection<ChallengeTo> findAll();

    ChallengeTo findChallengeById(long id);

    IdAware save(IdAware idAware);

    void delete(long id);
    
    public Long getNextId();
}
