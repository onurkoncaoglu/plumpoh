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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Suit;

public class ConsoleClientCommunicator implements ClientCommunicator {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    
    public int retrieveBid() {
        System.out.println("How many tricks?");
        System.out.print(": ");
        
        return getInt();
    }

    public Card retrieveCard() {
        System.out.println("What card do you want to play?");
        System.out.print(": ");
        
        Suit suit = Suit.valueOf(getString().toUpperCase());
        Card card = new Card(getInt(), suit);
        
        return card;
    }
    
    public void sendHand(Collection<Card> hand) {
        
        for (Card card : hand) {
            System.out.print(card + ", ");
        }
        
        System.out.println();
    }
    
    private String getString() {
        String text = "";
        
        try {
            text = in.readLine();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return text;
    }
    
    private int getInt() {
        return Integer.parseInt(getString());
    }
    
}
