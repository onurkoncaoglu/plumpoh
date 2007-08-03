package com.emilsebastian.plump.gui.graphic;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class PlumpAbstractCompositeGraphic extends PlumpAbstractGraphic {

    private List<PlumpAbstractGraphic> children = new ArrayList<PlumpAbstractGraphic>();
    
    
    @Override
    public void paint(Graphics2D g) {
        paintGraphic(g);
        paintChildren(g);
    }
    
    
    private final void paintChildren(Graphics2D g) {
        for (PlumpGraphic child : children) {
            child.paint(g);
        }
    }
    
    
    public PlumpGraphic getGraphicByCoordinates(int x, int y) {
        
        for (PlumpGraphic child : children) {
            PlumpGraphic graphic = child.getGraphicByCoordinates(x, y);
            
            if (graphic != null) {
                return graphic;
            }
        }
        
        return super.getGraphicByCoordinates(x, y);
    }

    
    protected final void addChild(PlumpAbstractGraphic child) {
        children.add(child);
    }
    
}
