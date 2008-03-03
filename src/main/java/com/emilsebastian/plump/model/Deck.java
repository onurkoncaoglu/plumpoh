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
import java.util.Collections;
import java.util.List;

import com.emilsebastian.plump.exception.EmptyDeckException;

/**
 * This class represents a deck of playing cards.
 * @author emilsebastian
 *
 */
public class Deck {

    private List<Card> deck = new ArrayList<Card>(52);
    
    
    /**
     * Constructor for Deck.
     * @param shuffle Whether to shuffle the deck or not
     */
    public Deck(boolean shuffle) {
        
        initializeDeck();
        
        if (shuffle) {
            Collections.shuffle(deck);
        }
    }
    
    /**
     * Creates an initial, sorted deck with 52 cards.
     *
     */
    private void initializeDeck() {
       
        for (Suit suit : Suit.values()) {
            for (int rank = 1; rank <= 13; rank++) {
                deck.add(new Card(rank, suit));
            }
        }
    }
    
    /**
     * Draws the top card of the deck.
     * @return the top card
     */
    public Card drawCard() {
        
        if (deck.isEmpty()) {
            throw new EmptyDeckException("No more cards in deck.");        
        }

        return deck.remove(0);
    }
    
}
