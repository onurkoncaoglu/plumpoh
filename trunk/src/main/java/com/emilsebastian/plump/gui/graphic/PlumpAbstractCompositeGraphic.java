package com.emilsebastian.plump.gui.graphic;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
    
    
    public PlumpGraphic getGraphicByCoordinates(Point position) {
        
        /*
         * The list of children is traversed in reversed order since
         * we want to return the object that was painted last as this
         * object will appear to be on top of the others.
         */
        
        ListIterator<PlumpAbstractGraphic> iterator =
            children.listIterator(children.size());
        
        while (iterator.hasPrevious()) {
            PlumpGraphic child  = iterator.previous();
            PlumpGraphic match = child.getGraphicByCoordinates(position);
            
            if (match != null) {
                return match;
            }
        }
        
        return super.getGraphicByCoordinates(position);
    }

    
    protected final void addChild(PlumpAbstractGraphic child) {
        children.add(child);
    }
    
}
