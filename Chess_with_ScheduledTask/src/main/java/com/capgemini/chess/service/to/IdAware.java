package com.capgemini.chess.service.to;

/**
 * Transport object characterized by id number.
 * 
 * @author PPATRONI
 *
 */
public abstract class IdAware {

    public abstract Long getId();
    public abstract void setId(Long id);
}
