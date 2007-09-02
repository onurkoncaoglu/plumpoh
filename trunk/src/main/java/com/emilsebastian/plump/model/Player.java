/*
 * Copyright 2007 Emil Jönsson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emilsebastian.plump.model;

import java.util.HashMap;
import java.util.Map;

import com.emilsebastian.plump.ClientCommunicator;
import com.emilsebastian.plump.exception.PlumpException;

/**
 * This class represents a card player.
 * @author emilsebastian
 *
 */
public class Player {
    
    private final Map<Integer, Integer> totalScore = new HashMap<Integer, Integer>(2, 2);
    private final Hand hand = new Hand();
    
    private final ClientCommunicator communicator;
    private final String name;
    
    private int bidTricks = 0;
    private int tricks = 0;
    
    
    /**
     * Creates a new player.
     * @param communicator
     * @param name
     */
    public Player(ClientCommunicator communicator, String name) {
        this.communicator = communicator;
        this.name = name;
    }
    
    /**
     * Resets the player's hand.
     *
     */
    public void clearHand() {
        hand.clearHand();
    }
    
    /**
     * Adds a card to the player's hand. 
     * @param card card to add
     */
    public void takeDealtCard(Card card) {
        hand.addCard(card);
    }
    
    /**
     * Removes the given card from the player's hand.
     * @param card the card to remove
     * @return <code>true</code> if the card was removed, else <code>false</code>
     */
    public boolean playCard(Card card) {
        return hand.discardCard(card);
    }
    
    /**
     * Tests if the player has the given card in hand.
     * @param card
     * @return <code>true</code> if the player has the card in hand, 
     * else <code>false</code>
     */
    public boolean hasCard(Card card) {
        return hand.hasCard(card);
    }
    
    /**
     * Tests if the player has a card of the given suit in hand.
     * @param suit
     * @return <code>true</code> if the player has a card of the given suit, 
     * else <code>false</code>
     */
    public boolean hasCardOfSuit(Suit suit) {
        return hand.hasCardOfSuit(suit);
    }
    
    /**
     * Increases the number of tricks taken by the player by one.
     *
     */
    public void addTrick() {
        this.tricks++;
    }
    
    /**
     * Returns the number of tricks taken by the player.
     * @return number of tricks
     */
    public int getTricks() {
        return tricks;
    }
    
    /**
     * Sets the number of tricks taken to the given value.
     * @param tricks number of tricks taken
     */
    public void setTricks(int tricks) {
        this.tricks = tricks;
    }

    /**
     * Returns the number of tricks the player has bid for.
     * @return number of bid tricks
     */
    public int getBidTricks() {
        return bidTricks;
    }

    /**
     * Sets the number of tricks the player has bid for.
     * @param bidTricks
     */
    public void setBidTricks(int bidTricks) {
        this.bidTricks = bidTricks;
    }
    
    /**
     * Return the name of the player.
     * @return the player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the score for the given round to the value given.
     * @param round current round
     * @param score the score of the round
     */
    public void addScore(int round, int score) {
        totalScore.put(round, score);
    }
    
    /**
     * Returns the score for a specific round.
     * @param deal
     * @return
     */
    public int getScore(int round) {
        return totalScore.get(round);
    }
    
    /**
     * Returns the player's hand.
     * @return the hand
     */
    public final Hand getHand() {
        return hand;
    }
    
    
    /* "networking" methods, eh ... */

    public int placeBid() {
        return communicator.retrieveBid();
    }

    public Card askForCard() throws PlumpException {
        tellHand();
        
        return communicator.retrieveCard();
    }
    
    public void tellHand() {
        communicator.sendHand(hand.getCards());
    }

}
