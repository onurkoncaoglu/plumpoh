package com.emilsebastian.plump.model;

public class Card {

    private Suit suit;
    private int value;
    
    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
    
    
    public String toString() {
        return suit + " " + value;
    }
    

    @Override
    public boolean equals(Object obj) {
      
        if (obj instanceof Card) {
            Card card = (Card) obj;
            
            return (this.suit == card.getSuit() &&
                    this.value == card.getValue());            
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return 100 * suit.ordinal() + value;
    }
    
}
