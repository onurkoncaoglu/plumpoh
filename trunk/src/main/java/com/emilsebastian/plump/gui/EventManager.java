package com.emilsebastian.plump.gui;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.model.Card;

public class EventManager {

    private final static Logger log = Logger.getLogger(EventManager.class);
    
    
    public synchronized void cardSelected(Card card) {
        log.info("Selected card " + card.getSuit().name() +
                " " + card.getNumber());
    }
    
}
