package com.emilsebastian.plump.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerList implements Iterable<Player> {

    private List<Player> players;
    
    private int dealingPlayer = 0;
    private int startingPlayer = 0;
    
   
    public PlayerList() {
        players = new ArrayList<Player>();
    }
    
    public void add(Player player) {
        players.add(player);
        startingPlayer = getStartingPlayerBasedOnDealer();
    }

    public int size() {
        return players.size();
    }

    public Player getStartingPlayer() {
        return players.get(startingPlayer);
    }
    
    public void setStartingPlayer(Player player) {
        startingPlayer = players.indexOf(player);
    }
    
    /**
     * Sets the player after the current dealer to dealer. Also sets the 
     * player after the new dealer to be the starting player.
     */
    public void nextDeal() {
        ++dealingPlayer;
        startingPlayer = getStartingPlayerBasedOnDealer();
    }
    
    public Iterator<Player> iterator() {

        return new Iterator<Player>() {
            
            int current = startingPlayer;
            
            
            public boolean hasNext() {
                return current < players.size() + startingPlayer;
            }

            public Player next() {
                return players.get(current++ % players.size());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }

    private int getStartingPlayerBasedOnDealer() {
        return (dealingPlayer + 1) % players.size();
    }
}
