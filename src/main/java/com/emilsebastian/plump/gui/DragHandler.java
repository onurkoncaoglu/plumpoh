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

package com.emilsebastian.plump.gui;

import java.awt.Component;
import java.awt.Point;

/**
 * Handles dragging of user interface elements.
 * @author emilsebastian
 *
 */
public class DragHandler {

    private Point offset = new Point(0, 0);

    
    /**
     * Stores the coordinates, relative to the component, of where 
     * the user pressed down the mouse button.
     * @param offset
     */
    public void mousePressed(Point offset) {
        this.offset = offset;
    }
    
    
    /**
     * Moves the specified component to the destination coordinates. 
     * The offset of the point where the user initially pressed 
     * down the mouse button is taken into account.
     * @param component
     * @param destination
     */
    public void mouseDragged(Component component, Point destination) {
        
        synchronized (component) {
            destination.x += component.getX() - offset.x;
            destination.y += component.getY() - offset.y;
            
            component.setLocation(destination);
        }
    }
    
}
