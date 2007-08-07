package com.emilsebastian.plump.event;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.emilsebastian.plump.gui.Clickable;
import com.emilsebastian.plump.gui.DragHandler;
import com.emilsebastian.plump.gui.Dragable;
import com.emilsebastian.plump.gui.GamePanel;
import com.emilsebastian.plump.gui.graphic.PlumpGraphic;

public class PlumpMouseListener implements MouseListener, MouseMotionListener {
    
    private final DragHandler dragHandler = new DragHandler();
    
    private final GamePanel gamePanel;
    
    
    public PlumpMouseListener(GamePanel panel) {
        this.gamePanel = panel;
    }
    
    public void mouseClicked(MouseEvent event) {
        PlumpGraphic graphic = gamePanel.getGraphicByCoordinates(event.getPoint());
        
        if (graphic instanceof Clickable) {
            Clickable clickable = (Clickable) graphic;
            clickable.mouseClicked();
        }
    }

    public void mousePressed(MouseEvent event) {
        final Point position = event.getPoint();
        
        PlumpGraphic graphic = gamePanel.getGraphicByCoordinates(position);
        
        if (graphic instanceof Dragable) {
            dragHandler.startDragging((Dragable) graphic, position);
        }
    }

    public void mouseReleased(MouseEvent event) {
        
        if (dragHandler.isDragging()) {
            dragHandler.stopDragging();
        }
    }
    
    public void mouseDragged(MouseEvent event) {
        
        if (dragHandler.isDragging()) {
            dragHandler.mouseDragged(event.getPoint());
            gamePanel.repaint();
        }
    }
    

    public void mouseEntered(MouseEvent event) {}
    
    public void mouseExited(MouseEvent event) {}
    
    public void mouseMoved(MouseEvent event) {}

}
