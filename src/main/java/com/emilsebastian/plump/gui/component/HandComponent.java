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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.emilsebastian.plump.gui.event.EventManager;
import com.emilsebastian.plump.gui.event.CardMouseListener;
import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Hand;

/**
 * This class represents the user interface element used
 * to display the cards a player currently has in hand.
 * 
 * @author emilsebastian
 *
 */
public class HandComponent extends JPanel {
    
    private final CardMouseListener listener = new CardMouseListener();
    
    private final Hand hand;
    private final EventManager eventManager;
    
    
    public HandComponent(Hand hand, Dimension size, EventManager eventManager) {
        this.hand = hand;
        this.eventManager = eventManager;
        
        setLayout(null);
        setSize(size);
        createCardComponents();
    }
    
    
    private void createCardComponents() {

        int count = 0;
        
        for (Card card : hand.getCards()) {            
            
            int x = 5 + 80 * count++;
            int y = 10;
            
            CardComponent cardGraphic = new CardComponent(
                    card, x, y, eventManager);
            
            cardGraphic.addMouseListener(listener);
            cardGraphic.addMouseMotionListener(listener);
            
            add(cardGraphic);
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
  
}
