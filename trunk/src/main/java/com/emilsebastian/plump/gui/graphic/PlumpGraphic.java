package com.emilsebastian.plump.gui.graphic;

import java.awt.Graphics2D;
import java.awt.Point;

public interface PlumpGraphic {

    public void paint(Graphics2D g);
    
    public PlumpGraphic getGraphicByCoordinates(Point position);

    public int getX();
    public void setX(int x);

    public int getY();
    public void setY(int y);
    
}
