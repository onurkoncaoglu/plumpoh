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

package com.emilsebastian.plump;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.exception.IllegalMoveException;
import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Deck;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;
import com.emilsebastian.plump.model.Suit;

/**
 * This class implements the main game loop.
 * @author emilsebastian
 *
 */
public class Game implements Runnable {

    private static final Logger log = Logger.getLogger(Game.class);
    
    private final PlumpRules rules = new PlumpRules();
    
    private PlayerList players;
    private Deck deck = new Deck(true);
    
    private int currentNumberOfCards;


    public Game(PlayerList players) {
        this.players = players;
    }


    public void run() {
    	
    	synchronized (this) {
    		try {
    			wait(1000);
    		
    		} catch (InterruptedException e) {
    			log.warn("Thread was interrupted ...");
    		}
		}
    	
        int initialNumberOfCards = rules.getInitialNumberOfCards(players.size());
        
        for (currentNumberOfCards = initialNumberOfCards;
            currentNumberOfCards >= 1; currentNumberOfCards--) {
            
            dealCards();
            retrieveNumberOfTricksBid();
            playHand();
            
            // let the next player "deal" the cards
            players.setNextDealer();
        }
    }
    
    
    private void dealCards() {
        
        for (Player player : players) {
            player.clearHand();

            for (int i = 1; i <= currentNumberOfCards; i++) {
                player.takeDealtCard(deck.drawCard());
            }

            player.setTricks(0);
            player.setBidTricks(0);
            player.tellHand();
        }
    }
    
    
    private void retrieveNumberOfTricksBid() {
        int bidTricksCount = 0;

        for (Player player : players) {
            boolean isLastBid = players.isLast(player);
            int bid = 0;
            
            do {
                bid = player.retrieveBid();
                
            } while (!rules.validBid(bid, currentNumberOfCards, 
                    bidTricksCount, isLastBid));
            
            player.setBidTricks(bid);
            bidTricksCount += bid;
        }
    }
    
    
    private void playHand() {
        
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
            
            /*
             * Make the player who won the round the starting player of the
             * next round and increase that player's trick count by one.
             */
            
            players.setStartingPlayer(leadingPlayer);
            leadingPlayer.addTrick();

            log.debug("\"" + leadingPlayer.getName() + "\" won this trick");
        }
        
        // set the score for each player for the current deal
        rules.calculateScore(players, currentNumberOfCards);
    }
    
    private Card askForCard(Player player, Suit currentSuit) {
        
        Card card = player.askForCard();
        
        if (rules.validMove(player, card, currentSuit)) {
            player.playCard(card);
            
            return card;
        
        } else {
            throw new IllegalMoveException(rules.getMessageFlash());
        }
    }

}
