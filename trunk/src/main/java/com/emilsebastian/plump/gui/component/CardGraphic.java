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

package com.emilsebastian.plump.gui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import com.emilsebastian.plump.gui.event.EventManager;
import com.emilsebastian.plump.model.Card;

public class CardGraphic extends JComponent {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 90;
    
    private final Card card;
    private final EventManager eventManager;
    
    
    public CardGraphic(Card card, EventManager eventManager) {
        this(card, 0, 0, eventManager);
    }
    
    public CardGraphic(Card card, int x, int y, EventManager eventManager) {
        this.card = card;
        this.eventManager = eventManager;
        
        setLocation(x, y);
        setSize(WIDTH, HEIGHT);
    }

    public void click() {
        eventManager.cardSelected(card);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        GradientPaint gradient = new GradientPaint(
                0, 0, Color.gray, WIDTH, HEIGHT, Color.darkGray);
        
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

        Font font = new Font("Verdana", Font.BOLD, 8);

        g2d.setFont(font);
        g2d.setColor(Color.white);
        g2d.drawString(card.getSuit().toString(), 5, 15);
        g2d.setFont(font.deriveFont(10f));
        g2d.drawString(Integer.toString(card.getRank()), 5, 30);
    }

}