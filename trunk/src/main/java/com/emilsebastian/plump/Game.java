package com.emilsebastian.plump;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.exception.PlumpException;
import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Deck;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;
import com.emilsebastian.plump.model.Suit;

public class Game implements Runnable {

    private static final Logger log = Logger.getLogger(Game.class);
    
    private final PlumpRules rules = new PlumpRules();
    private final Thread gameThread = new Thread(this);
    
    private PlayerList players;
    private Deck deck = new Deck();
    
    private int currentNumberOfCards;


    public Game(PlayerList players) {
        this.players = players;
    }
    
    
    public void start() {
        gameThread.start();
    }
    
    
    public void run() {
        try {
            int startingNumberOfCards = 52 / players.size();
            
            if (startingNumberOfCards > 3) {
                startingNumberOfCards = 3;
            }
            
            for (int numberOfCards = startingNumberOfCards;
                numberOfCards >= 1; numberOfCards--) {
                
                currentNumberOfCards = numberOfCards;
                
                deck.shuffle();
    
                dealCards();
                retrieveNumberOfTricksBid();
                playRounds();                
                
                // let the next player deal the cards
                players.nextDeal();
            }
            
        } catch (PlumpException e) {
            log.error(e.getMessage());
        }
    }
    
    
    private void dealCards() throws PlumpException {

        for (Player player : players) {
            player.resetHand();

            for (int i = 1; i <= currentNumberOfCards; i++) {
                player.takeDealtCard(deck.dealCard());
            }

            player.setTricks(0);
            player.setBidTricks(0);
            player.tellHand();
        }
    }
    
    
    private void retrieveNumberOfTricksBid() {
        for (Player player : players) {
            int tricks = player.placeBid();
            player.setBidTricks(tricks);
        }
    }
    
    
    private void playRounds() throws PlumpException {
        
        // let the player who has placed the highest bid start
        rules.setBeginnerBasedOnBidTricks(players);
        
        for (int round = 1; round <= currentNumberOfCards; round++) {
            
            Card leadingCard = null;
            Player leadingPlayer = null;
            Suit currentSuit = null;
            
            for (Player player : players) {
                
                Card card = askForCard(player, currentSuit);
                
                if (currentSuit == null) {
                    currentSuit = card.getSuit();
                    log.debug("current suit set to " + currentSuit);
                }
                
                if (rules.beats(card, leadingCard)) {
                    leadingCard = card;
                    leadingPlayer = player;
                }
            }
            
            leadingPlayer.addTrick();
            log.debug("\"" + leadingPlayer.getName() + "\" won this trick");
        }
        
        // set the score for each player for the current deal
        rules.calculateScore(players, currentNumberOfCards);
    }
    
    private Card askForCard(Player player, Suit currentSuit)
            throws PlumpException {
        
        Card card = player.askForCard();
        
        if (rules.validMove(player, card, currentSuit)) {
            player.playCard(card);
            
            return card;
        
        } else {
            throw new PlumpException(rules.getMessageFlash());
        }
    }
}
