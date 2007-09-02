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

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.emilsebastian.plump.gui.component.GameBoardGraphic;
import com.emilsebastian.plump.gui.event.EventManager;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;

public class Plump {

    private final ClientCommunicator communicator = 
        new ConsoleClientCommunicator();

    private PlayerList players = new PlayerList();
    
    
    private void initializeGame() {
        players.add(new Player(communicator, "Bengt"));
        players.add(new Player(communicator, "Lisa"));
        //players.add(new Player(communicator, "Emil"));
        
        Game game = new Game(players);
        game.start();
    }
    
    
    private void createAndShowGUI() {
        for (Player player : players) {
            createWindow(player);
        }        
    }


    private void createWindow(final Player player) {
        
        final EventManager eventManager = new EventManager();
        final GameBoardGraphic board = new GameBoardGraphic(player.getHand(), 0, 0, eventManager);
        
        JFrame frame = new JFrame(player.getName() + " - Plumpoh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, board.getPanel());
        frame.getContentPane().add(BorderLayout.SOUTH, new JButton("Hej"));
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               Plump plump = new Plump();
               plump.initializeGame();
               plump.createAndShowGUI();
           }
        });
    }
}
