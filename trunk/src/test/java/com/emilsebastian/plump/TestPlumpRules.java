package com.emilsebastian.plump;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.emilsebastian.plump.PlumpRules;
import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;
import com.emilsebastian.plump.model.Suit;


public class TestPlumpRules {
    
    public static final Logger log = Logger.getLogger(TestPlumpRules.class);
    
    PlayerList players = new PlayerList();
    
    Player kajsa = new Player(null, "Kajsa");
    Player hasse = new Player(null, "Hasse");
    Player maria = new Player(null, "Maria");
    
    Card diamonds_3 = new Card(Suit.DIAMONDS, 3);
    Card hearts_3 = new Card(Suit.HEARTS, 3);
    Card hearts_9 = new Card(Suit.HEARTS, 9);

    PlumpRules rules = new PlumpRules();
    

    @Before
    public void setup() {
        players.add(kajsa);
        players.add(hasse);
        players.add(maria);
    }

    @Test
    public void beats() {
        assertFalse(rules.beats(diamonds_3, hearts_3));
        assertFalse(rules.beats(hearts_3, diamonds_3));
        assertFalse(rules.beats(hearts_3, hearts_9));
        assertTrue(rules.beats(hearts_9, hearts_3));
    }
    
    @Test
    public void calculateScore() {
        int deal = 1;
        
        kajsa.setBidTricks(2);
        kajsa.setTricks(2);
        
        hasse.setBidTricks(2);
        hasse.setTricks(0);
        
        rules.calculateScore(players, deal);
        
        assertTrue("Succeding with 2 tricks bid gives 12 points",
                kajsa.getScore(deal) == 12);
        
        assertTrue("Failing with 2 tricks bid gives no points",
                hasse.getScore(deal) == 0);
    }
    
    @Test
    public void calculateScoreWithZeroTricksBid() {
        int deal = 3;
     
        kajsa.setBidTricks(0);
        kajsa.setTricks(0);
        
        hasse.setBidTricks(0);
        hasse.setTricks(1);
        
        rules.calculateScore(players, deal);
        
        assertTrue("Succeding with 0 expected sticks gives 5 points",
                kajsa.getScore(deal) == 5);
        
        assertTrue("Failing with 0 expected sticks gives no points",
                hasse.getScore(deal) == 0);
    }
    
    @Test
    public void setBeginnerBasedOnBidTricks() {
        Iterator it = setUpBidding(5, 6, 2);
        
        // let Hasse make the highest bid
        assertEquals(it.next(), hasse);
        assertEquals(it.next(), maria);
        assertEquals(it.next(), kajsa);

        // let Maria make the highest bid
        it = setUpBidding(3, 2, 4);
        
        assertEquals(it.next(), maria);
        assertEquals(it.next(), kajsa);
        assertEquals(it.next(), hasse);
    }
    
    @Test
    public void setBeginnerBasedOnBidTricksWithEqualBids() {
        Iterator it = setUpBidding(5, 5, 5);
        
        assertEquals(it.next(), hasse);
        assertEquals(it.next(), maria);
        assertEquals(it.next(), kajsa);
        
        // make Hasse the dealer
        players.setNextDealer();
        it = players.iterator();
        
        assertEquals(it.next(), maria);
        assertEquals(it.next(), kajsa);
        assertEquals(it.next(), hasse);
        
        // make Maria the dealer
        players.setNextDealer();
        it = players.iterator();
        
        assertEquals(it.next(), kajsa);
        assertEquals(it.next(), hasse);
        assertEquals(it.next(), maria);
    }
    
    private Iterator setUpBidding(int kajsaBid, int hasseBid, int mariaBid) {
        kajsa.setBidTricks(kajsaBid);
        hasse.setBidTricks(hasseBid);
        maria.setBidTricks(mariaBid);
        
        rules.setBeginnerBasedOnBidTricks(players);
        
        return players.iterator();
    }
    
    @Test
    public void followsSuit() {
        // compare two cards
        assertTrue(rules.followsSuit(hearts_3, hearts_9));
        assertFalse(rules.followsSuit(diamonds_3, hearts_3));

        // compare a card and a suit
        assertTrue(rules.followsSuit(diamonds_3, Suit.DIAMONDS));
        assertFalse(rules.followsSuit(hearts_3, Suit.CLUBS));
    }
    
    @Test
    public void validBid() {
        assertTrue("A valid bid",
                rules.validBid(2, 5, 1, false));
        
        assertTrue("A valid bid",
                rules.validBid(2, 5, 1, true));
        
        assertTrue("A valid bid", 
                rules.validBid(4, 5, 4, true));
        
        assertFalse("Not valid, bid too low", 
                rules.validBid(-1, 5, 1, false));
        
        assertFalse("Not valid, bid too high", 
                rules.validBid(6, 5, 1, false));
        
        assertFalse("Not valid, sum of tricks is equal to number of cards on hand", 
                rules.validBid(3, 5, 2, true));
        
        assertFalse("Not valid, sum of tricks is equal to number of cards on hand",
                rules.validBid(0, 5, 5, true));
    }
    
    @Test
    public void validMove() {
        kajsa.takeDealtCard(hearts_3);
        kajsa.takeDealtCard(diamonds_3);
        
        assertTrue("Playing a card from hand and having no suit set is valid",
                rules.validMove(kajsa, hearts_3, null));
        
        assertTrue("Playing a card from hand of the correct suit is valid",
                rules.validMove(kajsa, hearts_3, Suit.HEARTS));
        
        assertTrue("It's ok not to follow suit if it's impossible",
                rules.validMove(kajsa, hearts_3, Suit.CLUBS));
        
        assertFalse("Must follow suit if possible",
                rules.validMove(kajsa, hearts_3, Suit.DIAMONDS));
        
        assertFalse("Playing a card which is not in hand is not valid",
                rules.validMove(kajsa, hearts_9, Suit.HEARTS));        
    }
    
}
