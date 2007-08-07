package com.emilsebastian.plump.gui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;

import javax.swing.JPanel;

import com.emilsebastian.plump.gui.graphic.PlumpGraphic;
import com.emilsebastian.plump.gui.graphic.GameBoardGraphic;
import com.emilsebastian.plump.model.Card;

public class GamePanel extends JPanel {
    
    private final GameBoardGraphic gameBoardGraphic;
    
    
    public GamePanel(Collection<Card> cards, EventManager eventManager) {
        gameBoardGraphic = new GameBoardGraphic(cards, 500, 300, eventManager);
        setPreferredSize(new Dimension(500, 300));
    }
    
    
    public PlumpGraphic getGraphicByCoordinates(Point position) {
        return gameBoardGraphic.getGraphicByCoordinates(position);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Composite composite = g2d.getComposite();
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(composite);
        
        gameBoardGraphic.paint(g2d);
    }

}
