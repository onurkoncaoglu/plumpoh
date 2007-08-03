package com.emilsebastian.plump.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.apache.log4j.Logger;

import com.emilsebastian.plump.gui.Clickable;
import com.emilsebastian.plump.gui.Dragable;
import com.emilsebastian.plump.gui.GamePanel;
import com.emilsebastian.plump.gui.graphic.PlumpGraphic;

public class PlumpMouseListener implements MouseListener, MouseMotionListener {

    private final Logger log = Logger.getLogger(this.getClass());
    
    private final GamePanel gamePanel;
    
    private Dragable dragable;
    private boolean isDragging = false;
    
    
    public PlumpMouseListener(GamePanel panel) {
        this.gamePanel = panel;
    }
    
    
    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        
        PlumpGraphic graphic = gamePanel.getGraphicByCoordinates(x, y);
        
        if (graphic instanceof Clickable) {
            Clickable clickable = (Clickable) graphic;
            clickable.mouseClicked();
        }
    }

    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        
        PlumpGraphic graphic = gamePanel.getGraphicByCoordinates(x, y);
        
        if (graphic instanceof Dragable) {
            dragable = (Dragable) graphic;
            dragable.mousePressed(x, y);
            isDragging = true;
        }
    }

    public void mouseReleased(MouseEvent event) {
        isDragging = false;
    }
    
    public void mouseDragged(MouseEvent event) {
        
        if (isDragging) {
            dragable.mouseDragged(event.getX(), event.getY());
            gamePanel.repaint();
        }
    }
    

    public void mouseEntered(MouseEvent event) {}
    
    public void mouseExited(MouseEvent event) {}    
    
    public void mouseMoved(MouseEvent event) {}    

}
