package com.emilsebastian.plump.gui.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import com.emilsebastian.plump.gui.Clickable;
import com.emilsebastian.plump.gui.Dragable;
import com.emilsebastian.plump.gui.EventManager;
import com.emilsebastian.plump.model.Card;

public class CardGraphic extends PlumpAbstractGraphic implements Clickable, Dragable {
    
    private final static int cardWidth = 60;
    private final static int cardHeight = 90;
    
    private final static GradientPaint gradient = new GradientPaint(
            0, 0, Color.gray, cardWidth, cardHeight, Color.darkGray);
    
    private final static Font font = new Font("Verdana", Font.BOLD, 10);
    
    private final Card card;
    private final EventManager eventManager;
    
    
    public CardGraphic(Card card, EventManager eventManager) {
        this(card, 0, 0, eventManager);
    }
    
    public CardGraphic(Card card, int x, int y, EventManager eventManager) {
        this.card = card;
        this.eventManager = eventManager;
        
        setupDrawingArea(x, y, cardWidth, cardHeight);
        setupCardGraphic();
    }
    
    public void setupCardGraphic() {        
        
        final RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        qualityHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        image.setRenderingHints(qualityHints);
    }
    

    protected void paintGraphic(Graphics2D parentGraphic) {
        image.setPaint(gradient);
        image.fillRect(0, 0, cardWidth, cardHeight);
        image.setColor(Color.WHITE);
        image.setFont(font);
        image.drawString(card.getSuit().name(), 5, 15);
        image.drawString("" + card.getNumber(), 5, 30);
        
        paintBufferedImage(parentGraphic);
    }
    
    
    public void mouseClicked() {
        eventManager.cardSelected(card);
    }
    
    public Point getPosition() {
        return new Point(getX(), getY());
    }
    
    public void setPosition(Point position) {
        setX(position.x);
        setY(position.y);
    }
    

    public static int getCardWidth() {
        return cardWidth;
    }
    
    public static int getCardHeight() {
        return cardHeight;
    }

}