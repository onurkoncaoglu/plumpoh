package com.emilsebastian.plump.gui;

import java.awt.Point;

/**
 * Handles dragging of PlumpGraphic objects in the user interface.
 * @author emilsebastian
 *
 */
public class DragHandler {

    private Dragable dragable;
    private Point offset = new Point();

    private boolean isDragging = false;
    
    
    public void startDragging(Dragable dragable, Point drag) {
        this.dragable = dragable;        
        
        final Point position = dragable.getPosition(); 
        
        offset.x = drag.x - position.x;
        offset.y = drag.y - position.y;

        isDragging = true;
    }

    public void mouseDragged(Point drag) {        
        final Point position = new Point(drag.x - offset.x, drag.y - offset.y);
        dragable.setPosition(position);
    }
    
    public void stopDragging() {
        isDragging = false;
    }
    
    public boolean isDragging() {
        return isDragging;
    }
    
}
