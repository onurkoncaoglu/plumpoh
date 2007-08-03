package com.emilsebastian.plump.gui.graphic;

import java.awt.Graphics2D;

public interface PlumpGraphic {

    public void paint(Graphics2D g);
    
    public PlumpGraphic getGraphicByCoordinates(int x, int y);
    
    public int getX();
    
    public void setX(int x);

    public int getY();
    
    public void setY(int y);
    
}
