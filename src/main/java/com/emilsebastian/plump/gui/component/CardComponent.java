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

package com.emilsebastian.plump.gui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import com.emilsebastian.plump.model.Card;

/**
 * This class represents the user interface element used 
 * to display a playing card.
 * 
 * @author emilsebastian
 *
 */
public class CardComponent extends JComponent {

	private static final long serialVersionUID = 3813972052902995779L;

    private static final Dimension SIZE = new Dimension(60, 90);
    
    private final Card card;
    
    
    public CardComponent(Card card) {
        this(card, new Point(0, 0));
    }
    
    
    public CardComponent(Card card, Point position) {
        this.card = card;
        
        setLocation(position);
        setSize(SIZE);
    }
    
    
    /**
     * Tests if this component is representing a given <code>Card</code>.
     * @param other
     * @return <code>true</code> if this component represents the card, else <code>false</code>
     */
    public boolean isRepresentingCard(Card other) {
    	return card.equals(other);
    }
    
    
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        GradientPaint gradient = new GradientPaint(
                0, 0, Color.gray, SIZE.width, SIZE.height, Color.darkGray);
        
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, SIZE.width - 1, SIZE.height - 1);

        Font font = new Font("Verdana", Font.BOLD, 8);

        g2d.setFont(font);
        g2d.setColor(Color.white);
        g2d.drawString(card.getSuit().toString(), 5, 15);
        g2d.setFont(font.deriveFont(10f));
        g2d.drawString(Integer.toString(card.getRank()), 5, 30);
    }


	@Override public boolean equals(Object obj) {

		if (obj instanceof CardComponent) {
			CardComponent other = (CardComponent) obj;
			
			return other.card.equals(card);			
		}
		
		return false;
	}


	@Override public int hashCode() {
		return super.hashCode() + card.hashCode();
	}
    
}