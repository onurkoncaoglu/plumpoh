package com.emilsebastian.plump;

import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;
import com.emilsebastian.plump.model.Suit;

public class PlumpRules {
    
    private String message = "";
    

    /**
     * Makes the player who has placed the highest bid the starting player. In 
     * case two players place the same bid, the player who was first to place 
     * the highest bid is set to start.
     * 
     * @param players
     */
    public void setBeginnerBasedOnBidTricks(PlayerList players) {
        Player highestBidPlayer = players.getStartingPlayer();
        
        for (Player player : players) {
            int expectedNumber = player.getBidTricks();
            
            if (expectedNumber > highestBidPlayer.getBidTricks()) {
                highestBidPlayer = player;
            }
        }
        
        players.setStartingPlayer(highestBidPlayer);
    }
    
    
    /**
     * Tests if the card on the left hand side beats
     * the card on the right hand side.  
     * @param left
     * @param right
     * @return
     */
    public boolean beats(Card left, Card right) {
        
        if (left == null) {
            return false;
            
        } else if (right == null) {
            return true;
        }
        
        return (followsSuit(left, right) &&
                left.getNumber() > right.getNumber());
    }
    
    
    /**
     * Tests if two given cards are of the same suit.
     * @param left
     * @param right
     * @return
     */
    public boolean followsSuit(Card left, Card right) {
        return followsSuit(left, right.getSuit());
    }
    
    /**
     * Tests if a card is of a given suit.
     * @param card
     * @param suit
     * @return
     */
    public boolean followsSuit(Card card, Suit suit) {
        return card.getSuit() == suit;
    }
    
    
    /**
     * Sets the score for the given deal for each player. If the player 
     * has managed to take as many tricks as initially bid, the player gets
     * 10 points plus the number of tricks bid. An exception to this rule is 
     * if the player has bid zero tricks, in which case the player only gets 
     * five points.
     * <p>
     * If the player has over- or underbid, the player gets zero points.
     * 
     * @param players
     * @param round
     */
    public void calculateScore(PlayerList players, int deal) {
        for (Player player : players) {
            int tricks = player.getTricks();
            int score = 0;
            
            if (tricks == player.getBidTricks()) {
                score = (tricks > 0)? 10 + tricks : 5;
            }
            
            player.addScore(deal, score);
        }
    }


    /**
     * Tests whether a player can play a given card or not.
     * 
     * <ul>
     *   <li>The player must have the current card in hand</li>
     *   <li>The player must follow suit, unless the player has no card of the given suit in hand</li>
     * </ul>
     * 
     * @param player player making the move
     * @param card played card
     * @param currentSuit suit of current round
     * @return <code>true</code> if the move is valid, else <code>false</code>
     */
    public boolean validMove(Player player, Card card, Suit currentSuit) {
        
        if (player.hasCard(card) == false) {
            setMessage("No such card in hand.");
            return false;
        
        } else if (currentSuit != null &&
                followsSuit(card, currentSuit) == false &&
                player.hasCardOfSuit(currentSuit)) {
            
            setMessage("Player must follow suit.");            
            return false;
        }
        
        return true;
    }

    public String getMessageFlash() {
        final String flash = message;
        setMessage("");
        
        return flash;
    }
    
    private void setMessage(String message) {
        this.message = message;
    }
    
}
