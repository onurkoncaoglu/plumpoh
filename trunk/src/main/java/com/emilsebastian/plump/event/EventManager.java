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

package com.emilsebastian.plump.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.model.Card;

public class EventManager {

    private final static Logger log = Logger.getLogger(EventManager.class);
    
    private final Set<PlumpEventListener> listeners = 
    	Collections.synchronizedSet(new HashSet<PlumpEventListener>());
    
    
    public synchronized void cardSelected(Card card) {
        log.debug("Selected card " + card.getSuit().name() + " " + card.getRank());
    	notifyListeners(new CardSelectedEvent(card));
    }

    /**
     * Adds the specified event listener to receive plump events.
     * @param listener the plump event listener
     */
    public void addPlumpEventListener(PlumpEventListener listener) {
    	listeners.add(listener);
    }
    
    /**
     * Removes the specified listener so that it no longer receives plump events.
     * @param listener the plump event listener
     */
    public void removePlumpEventListener(PlumpEventListener listener) {
    	listeners.remove(listener);
    }
    
    /**
     * Notifies all listeners that an event has occurred.
     * @param event the event to be distributed to all listeners
     */
    private void notifyListeners(final PlumpEvent event) {
    	synchronized (listeners) {
	    	for (PlumpEventListener listener : listeners) {
    			listener.eventOccured(event);
	    	}
    	}
    }

}
