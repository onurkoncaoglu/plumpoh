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

package com.emilsebastian.plump.gui.event;

import java.awt.event.MouseEvent;

import com.emilsebastian.plump.event.EventManager;
import com.emilsebastian.plump.gui.DragHandler;
import com.emilsebastian.plump.model.Card;

/**
 * Listens for mouse events performed on a card component.
 * @author emilsebastian
 *
 */
public class CardMouseListener extends MouseListenerAdapter {
    
    private final DragHandler dragHandler = new DragHandler();
    
    private final Card card;
    private final EventManager eventManager;
    
    
    public CardMouseListener(Card card, EventManager eventManager) {
    	this.card = card;
    	this.eventManager = eventManager;
    }
    
    
    @Override public void mouseClicked(MouseEvent event) {
        eventManager.cardSelected(card);
    }
    
    
    @Override public void mousePressed(MouseEvent event) {
        dragHandler.mousePressed(event.getPoint());
    }

    
    @Override public void mouseDragged(MouseEvent event) {
        dragHandler.mouseDragged(event.getComponent(), event.getPoint());
    }

}
