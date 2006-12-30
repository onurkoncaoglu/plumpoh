package com.emilsebastian.plump.model;

import java.util.ArrayList;
import java.util.List;

import com.emilsebastian.plump.exception.PlumpException;

public class Deck {

    private List<Card> cards = new ArrayList<Card>(52);
    private int cardIndex = 0;
    
    
    /**
     * Shuffles the deck.
     *
     */
    public void shuffle() {
        
        initializeDeck();
        
        int numberOfCards = cards.size();
        
        for (int i = 0; i < numberOfCards; i++) {
            int randomIndex = (int)Math.floor(Math.random() * numberOfCards);

            /* swap positions */
            Card tempCard = cards.get(randomIndex);
            cards.set(randomIndex, cards.get(i));
            cards.set(i, tempCard);
        }
    }

    /**
     * Creates an initial, sorted deck with 52 cards.
     *
     */
    private void initializeDeck() {
        cards.clear();
        cardIndex = 0;
        
        for (Suit suit : Suit.values()) {
            for (int value = 1; value <= 13; value++) {
                cards.add(new Card(suit, value));
            }
        }
    }
    
    /**
     * Deals the top card of the deck.
     * @return the top card
     */
    public Card dealCard() throws PlumpException {
        
        if (cardIndex < cards.size()) {
            return cards.get(cardIndex++);
        
        } else {
            throw new PlumpException("No more cards in deck.");
        }
    }
    
    public void print() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
    
}
