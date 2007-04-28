package com.emilsebastian.plump.model;

public class Card {

    private Suit suit;
    private int number;
    
    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }
    
    
    public String toString() {
        return suit + " " + number;
    }
    

    @Override
    public boolean equals(Object obj) {
      
        if (obj instanceof Card) {
            Card card = (Card) obj;
            
            return (this.suit == card.getSuit() &&
                    this.number == card.getNumber());            
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return 100 * suit.ordinal() + number;
    }
    
}
