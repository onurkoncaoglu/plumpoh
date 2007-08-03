package com.emilsebastian.plump.gui.graphic;

import java.awt.Graphics2D;
import java.util.Collection;

import com.emilsebastian.plump.gui.EventManager;
import com.emilsebastian.plump.model.Card;

public class GameBoardGraphic extends PlumpAbstractCompositeGraphic {
    
    public GameBoardGraphic(Collection<Card> cards, int width, int height, 
            EventManager eventManager) {
        
        addChild(new HandGraphic(cards, 20, 100, eventManager));        
        setupDrawingArea(0, 0, width, height);
    }
    
    @Override
    protected void paintGraphic(Graphics2D parentGraphic) {
        paintBufferedImage(parentGraphic);
    }

}
