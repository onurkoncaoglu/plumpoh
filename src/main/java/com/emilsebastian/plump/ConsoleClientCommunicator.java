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
        Card card = new Card(suit, getInt());
        
        return card;
    }
    
    public void sendHand(Collection<Card> hand) {
        
        for (Card card : hand) {
            System.out.print(card + "\t");
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
