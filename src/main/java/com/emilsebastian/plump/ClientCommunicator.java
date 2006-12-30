package com.emilsebastian.plump;

import java.util.Collection;

import com.emilsebastian.plump.model.Card;

public interface ClientCommunicator {

    public Card retrieveCard();
    
    public int retrieveBid();
    
    public void sendHand(Collection<Card> hand);
}
