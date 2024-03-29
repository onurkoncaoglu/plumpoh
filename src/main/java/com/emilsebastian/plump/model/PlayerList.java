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
     * Tests if the given player is the last in line.
     * @param player
     * @return
     */
    public boolean isLast(Player player) {
        return (players.indexOf(player) + 1) % size() == startingPlayer;
    }
    
    /**
     * Sets the player after the current dealer to dealer. Also sets the 
     * player after the new dealer to be the starting player.
     */
    public void setNextDealer() {
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
