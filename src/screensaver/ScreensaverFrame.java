/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensaver;

import com.sun.jna.*;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import java.awt.HeadlessException;
import javax.swing.JPanel;

/**
 *
 * @author FPeter
 */
public class ScreensaverFrame extends javax.swing.JFrame{
    private JPanel panel;
    private InputHandler mouseHandler;
    private boolean previewMode;
    private long handleWindow;

    public ScreensaverFrame(JPanel panel) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        
        this.panel = panel;
        this.mouseHandler = new InputHandler();
        this.previewMode = false;
        setContentPane(panel);
    }

    public ScreensaverFrame(JPanel panel, long handleWindow) {
        this(panel);
        this.handleWindow = handleWindow;
        this.previewMode = true;
        
       
    }
    
    
    
    
    
}
