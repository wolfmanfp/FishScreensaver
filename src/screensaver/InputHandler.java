/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensaver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tools.MathTools;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FPeter
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener{
    private int oldX = -1, oldY = -1;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(),
            y = e.getY();
        double distance;
        
        if (oldX == -1) {
            oldX = x;
            oldY = y;
            return;
        }
        
        distance = MathTools.distance(oldX, x, oldY, y);
        if (distance > 10) {
            try {
                Thread.sleep(50);
                System.exit(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        oldX = x;
        oldY = y;    
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
                                                                                                                                                                                         'd8\u-1450