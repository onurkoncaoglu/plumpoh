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

package com.emilsebastian.plump;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.event.CardSelectedEvent;
import com.emilsebastian.plump.event.EventManager;
import com.emilsebastian.plump.event.PlumpEvent;
import com.emilsebastian.plump.event.PlumpEventListener;
import com.emilsebastian.plump.model.Card;
import com.emilsebastian.plump.model.Hand;

public class GuiPlayerCommunicator implements PlayerCommunicator, PlumpEventListener {

	private static final Logger log = Logger.getLogger(GuiPlayerCommunicator.class);
	
	private final EventManager eventManager;
	
	private PlayerCommunicator communicator = new ConsoleClientCommunicator();
	
	private Card retrievedCard = null;
	
	
	public GuiPlayerCommunicator(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	
	public void initialize() {
		eventManager.addPlumpEventListener(this);
		log.debug("Communicator is now listening for Plump Events");
	}
	
	
	@Override public int retrieveBid() {
		return communicator.retrieveBid();
	}
	

	@Override public synchronized Card retrieveCard() {
		
		while (retrievedCard == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				log.warn("Thread interrupted: " + e.getMessage());
			}
		}
		
		Card result = retrievedCard;
		retrievedCard = null;
		
		return result;
	}

	
	@Override public void sendHand(Hand hand) {
		communicator.sendHand(hand);
	}

	
	@Override public synchronized void eventOccured(PlumpEvent plumpEvent) {
		
		switch (plumpEvent.getName()) {
		
		case BID_MADE :
			break;
		
		case CARD_SELECTED :
			CardSelectedEvent event = (CardSelectedEvent) plumpEvent;
			retrievedCard = event.getCard();
			log.debug("Card Selected Event: " + retrievedCard);
			break;		
		
		}
		
		notifyAll();		
	}

}
