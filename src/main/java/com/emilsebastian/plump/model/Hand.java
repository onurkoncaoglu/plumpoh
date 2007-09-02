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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class represents a player's hand.
 * @author emilsebastian
 *
 */
public class Hand {

    private final Collection<Card> cards = 
        Collections.synchronizedCollection(new ArrayList<Card>());
    
    
    /**
     * Removes all cards from the hand.
     *
     */
    public void clearHand() {
        cards.clear();
    }
    
    /**
     * Adds a card to the hand. 
     * @param card card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }
    
    /**
     * Removes the given card from the hand.
     * @param card the card to remove
     * @return <code>true</code> if the card was removed, else <code>false</code>
     */
    public boolean discardCard(Card card) {
        return cards.remove(card);
    }
    
    /**
     * Tests if the hand contains the given card.
     * @param card
     * @return <code>true</code> if the card is in hand, 
     * else <code>false</code>
     */
    public boolean hasCard(Card card) {
        return cards.contains(card);
    }
    
    /**
     * Tests if the hand contains a card of the given suit.
     * @param suit
     * @return <code>true</code> if the player has a card of the given suit, 
     * else <code>false</code>
     */
    public boolean hasCardOfSuit(Suit suit) {
        
        for (Card card : cards) {
            if (card.getSuit() == suit) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns a collection of all cards currently in hand.
     * @return the cards in hand.
     */
    public Collection<Card> getCards() {
        return Collections.unmodifiableCollection(cards);
    }
    
}
