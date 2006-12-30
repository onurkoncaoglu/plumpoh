package com.emilsebastian.plump;

import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;

public class Plump {

    private final ClientCommunicator communicator = 
        new ConsoleClientCommunicator();
    
    private Game plumpGame;


    public static void main(String[] args) {
        new Plump();
    }
    
    public Plump() {
        PlayerList players = new PlayerList();
        
        players.add(new Player(communicator, "Bengt"));
        players.add(new Player(communicator, "Lisa"));
        
        plumpGame = new Game(players);
        plumpGame.start();
    }

}
