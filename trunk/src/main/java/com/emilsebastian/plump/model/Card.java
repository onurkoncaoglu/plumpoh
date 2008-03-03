/*
 * Copyright 2007 - 2008 Emil Jönsson
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

/**
 * This class represents a playing card.
 * @author emilsebastian
 *
 */
public class Card implements Comparable<Card> {

    private final int rank;
    private final Suit suit;
    
    
    public Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    
    public Suit getSuit() {
        return suit;
    }

    
    public int getRank() {
        return rank;
    }

    
    /**
     * Compares this card with the specified card for order. If the suits of
     * the two cards match they are compared by rank, else the cards will be
     * compared based on the order in which the suits are defined in {@link Suit}.
     */
    public int compareTo(Card other) {
		
    	if (other.suit == suit) {
    		return rank - other.rank;
    	}
    	
    	return suit.compareTo(other.suit);
	}
    

    @Override public boolean equals(Object obj) {
      
        if (obj instanceof Card) {
            Card card = (Card) obj;
            
            return (this.suit == card.getSuit() &&
                    this.rank == card.getRank());            
        }
        
        return false;
    }

    
    @Override public int hashCode() {
        return 100 * suit.ordinal() + rank;
    }
    
    
    @Override public String toString() {
        return suit + " " + rank;
    }
    
}
