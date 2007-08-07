package com.emilsebastian.plump.gui.graphic;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class PlumpAbstractGraphic implements PlumpGraphic {
    
    private int x = 0;
    private int y = 0;
    
    private int width = 0;
    private int height = 0;    
    
    protected BufferedImage bufferedImage;
    protected Graphics2D image;
    
   
    protected abstract void paintGraphic(Graphics2D g);
    
    
    public void paint(Graphics2D g) {
        paintGraphic(g);
    }
    
    
    protected void paintBufferedImage(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, null);
    }
    
    
    protected final void setupDrawingArea(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        bufferedImage = new BufferedImage(width, height, 
                BufferedImage.TYPE_INT_ARGB);

        image = bufferedImage.createGraphics();
    }

    
    public PlumpGraphic getGraphicByCoordinates(Point pos) {
        
        if (isInInterval(pos.x, x, width) && isInInterval(pos.y, y, height)) {
            return this;
        }
        
        return null;        
    }
    
    
    private boolean isInInterval(int number, int start, int offset) {
        return (number >= start && number <= (start + offset));
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
}
