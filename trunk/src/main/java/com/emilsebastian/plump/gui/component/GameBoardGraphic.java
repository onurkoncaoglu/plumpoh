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

import java.awt.Dimension;

import javax.swing.JPanel;

import com.emilsebastian.plump.gui.event.EventManager;
import com.emilsebastian.plump.model.Hand;

public class GameBoardGraphic {
    
    private final static Dimension SIZE = new Dimension(400, 200);
    
    private final JPanel boardPanel = new JPanel();
    
    
    public GameBoardGraphic(Hand hand, int width, int height, 
            EventManager eventManager) {

        HandGraphic handGraphic = new HandGraphic(hand, SIZE, eventManager);

        boardPanel.setLayout(null);
        boardPanel.setPreferredSize(SIZE);
        boardPanel.add(handGraphic);
    }
    
    public JPanel getPanel() {
        return boardPanel;
    }
    
}
