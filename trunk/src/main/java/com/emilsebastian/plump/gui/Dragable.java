package com.emilsebastian.plump.gui;

import java.awt.Point;

/**
 * Interface for PlumpGraphic objects that are to be dragable.
 * @author emilsebastian
 *
 */
public interface Dragable {

    public Point getPosition();
    
    public void setPosition(Point position);
    
}
