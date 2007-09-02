package com.emilsebastian.plump.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.Suit;

public class TestPlayer {

    Player kajsa = new Player(null, "Kajsa");
    
    Card diamonds_3 = new Card(3, Suit.DIAMONDS);
    Card hearts_3 = new Card(3, Suit.HEARTS);
    Card hearts_9 = new Card(9, Suit.HEARTS);
    
    
    @Before
    public void setup() {
        kajsa.takeDealtCard(diamonds_3);
        kajsa.takeDealtCard(hearts_3);
    }
    
    @Test
    public void takeDealtCard() {
        assertTrue(kajsa.hasCard(diamonds_3));
        assertTrue(kajsa.hasCard(hearts_3));        
        assertFalse(kajsa.hasCard(hearts_9));
    }

    @Test
    public void playCard() {
        kajsa.playCard(diamonds_3);
        kajsa.playCard(hearts_3);
        
        assertFalse(kajsa.hasCard(diamonds_3));
        assertFalse(kajsa.hasCard(hearts_3));
    }
}
