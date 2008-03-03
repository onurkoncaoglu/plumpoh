/*
 * Copyright 2007 - 2008 Emil Jönsson
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
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.event.EventManager;
import com.emilsebastian.plump.gui.GameBoardController;
import com.emilsebastian.plump.model.Player;
import com.emilsebastian.plump.model.PlayerList;

public class Plump {

	private final static Logger log = Logger.getLogger(Plump.class);
	private final static Dimension SIZE = new Dimension(600, 300);
	
    private final Map<Player, EventManager> eventManagers = 
        new HashMap<Player, EventManager>();

    private PlayerList players = new PlayerList();
    
    
    
    
    private void go() {
        
    	log.info("Initialising game ...");
    	
    	createPlayer("Lisa");
        createPlayer("Bengt");        
    	
        final Thread game = new Thread(new Game(players), "Game Thread");
        
    	SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               createAndShowGUI();
               game.start();
           }
        });
    	
    	log.info("Waiting for windows to be drawn ...");
    }
    
    
    private void createPlayer(String name) {
    	EventManager eventManager = new EventManager();
    	GuiPlayerCommunicator communicator = new GuiPlayerCommunicator(eventManager);
    	Player player = new Player(communicator, name);
    	
    	players.add(player);
    	eventManagers.put(player, eventManager);
    	
    	communicator.initialize();
    }
    
    
    private void createAndShowGUI() {
        for (Player player : players) {
            createWindow(player);
        }
    }


    private void createWindow(final Player player) {
        
        final GameBoardController controller = 
        	new GameBoardController(SIZE, eventManagers.get(player));
        
        JPanel board = controller.initializeGameBoard(player.getHand());
        JFrame frame = new JFrame(player.getName() + " - Plumpoh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, board);
        frame.getContentPane().add(BorderLayout.SOUTH, new JButton("Hej"));
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void main(String... arguments) {
    	new Plump().go();
    }
    
}
