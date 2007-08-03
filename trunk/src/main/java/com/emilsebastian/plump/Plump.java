package com.emilsebastian.plump;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.emilsebastian.plump.event.PlumpMouseListener;
import com.emilsebastian.plump.gui.EventManager;
import com.emilsebastian.plump.gui.GamePanel;
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
        final GamePanel plumpPanel = new GamePanel(player.getCards(), eventManager);
        
        PlumpMouseListener listener = new PlumpMouseListener(plumpPanel);
        
        plumpPanel.addMouseListener(listener);
        plumpPanel.addMouseMotionListener(listener);
        
        JFrame frame = new JFrame(player.getName() + " - Plumpoh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().add(BorderLayout.CENTER, plumpPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, new JButton("Hej"));
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
