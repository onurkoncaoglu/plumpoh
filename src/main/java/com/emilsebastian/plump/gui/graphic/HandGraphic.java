package com.emilsebastian.plump.gui.graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;

import com.emilsebastian.plump.gui.EventManager;
import com.emilsebastian.plump.model.Card;

public class HandGraphic extends PlumpAbstractCompositeGraphic {
    
    private static int width = 300;
    private static int height = 200;
    
    private final Collection<Card> cards;
    private final EventManager eventManager;
    
    
    public HandGraphic(Collection<Card> cards, EventManager eventManager) {
        this(cards, 0, 0, eventManager);
    }
    
    public HandGraphic(Collection<Card> cards, int x, int y, 
            EventManager eventManager) {
        
        this.cards = cards;
        this.eventManager = eventManager;

        setupDrawingArea(x, y, width, height);
        createHandGraphic();
    }
    
    
    private void createHandGraphic() {
        int count = 0;
        
        for (Card card : cards) {
            addChild(new CardGraphic(card,
                    getX() + (CardGraphic.getCardWidth() + 10) * count,
                    getY() + 30,
                    eventManager));
            count++;
        }
    }
    
  
    @Override
    protected void paintGraphic(Graphics2D parentGraphic) {
        
        image.setColor(Color.PINK);
        image.drawString("Antal kort: " + cards.size(), 5, 10);
        
        paintBufferedImage(parentGraphic);
    }

}
