/*
 * Copyright 2008 Emil Jönsson
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

package com.emilsebastian.plump.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.emilsebastian.plump.event.EventManager;
import com.emilsebastian.plump.gui.component.CardComponent;
import com.emilsebastian.plump.gui.component.HandComponent;
import com.emilsebastian.plump.gui.event.CardMouseListener;
import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Hand;
import com.emilsebastian.plump.model.listener.HandEventListener;

/**
 * The responsibility of the <code>GameBoardController</code> is to listen 
 * for game events and then update the UI accordingly.
 * 
 * @author emilsebastian
 *
 */
public class GameBoardController implements HandEventListener {
	
	private final Set<CardComponent> cardComponents = 
		new HashSet<CardComponent>();	
	
	private final EventManager eventManager;

	private final Dimension size;
	private final JPanel boardPanel = new JPanel();
	
	private HandComponent handComponent;
	
	
	public GameBoardController(Dimension size, EventManager eventManager) {
		this.size = size;
		this.eventManager = eventManager;
		
		handComponent = new HandComponent(size);
	}
	

	/**
	 * Initialises a component showing the game board and all the cards the player has in hand.
	 * @param hand the hand that is to be shown on the game board
	 * @return the initialised game board component
	 */
	public JPanel initializeGameBoard(Hand hand) {		
		
		for (Card card : hand.getCards()) {
			addCardComponent(card);
		}
		
		boardPanel.setLayout(null);
        boardPanel.setPreferredSize(size);
        boardPanel.add(handComponent);
        
        // listen for changes in the hand
        hand.addHandEventListener(this);
        
        return boardPanel;
	}
	
	
	private void addCardComponent(Card card) {
		
		Point position = new Point(30 * cardComponents.size(), 
				10 + cardComponents.size() % 2 * 100);

		CardComponent component = new CardComponent(card, position);		
		CardMouseListener listener = new CardMouseListener(card, eventManager);
		
		component.addMouseListener(listener);
		component.addMouseMotionListener(listener);
		
		// add the component to the panel and repaint
		handComponent.add(component);
		handComponent.repaint();
		
		// save a reference to each card component
		cardComponents.add(component);
	}
	
	
	@Override public synchronized void cardAdded(final Card card) {		
		SwingUtilities.invokeLater(new Runnable() {
           public void run() {
        	   addCardComponent(card);
           }
        });
	}
	

	@Override public synchronized void cardRemoved(Card card) {

		Iterator<CardComponent> it = cardComponents.iterator();

		while (it.hasNext()) {
			
			final CardComponent component = it.next();
			
			if (component.isRepresentingCard(card)) {

				// remove the card component from the container	
				SwingUtilities.invokeLater(new Runnable() {
		           public void run() {
		        	   handComponent.remove(component);
		        	   handComponent.repaint();
		           }
		        });

				// ... and remove it from the set of card components too 
				it.remove();

				break;
			}
		}
	}

}
