/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.wolfman.fishscreensaver.util;

import java.awt.Image;
import java.util.Objects;
import javax.swing.ImageIcon;

public class ImageUtil {
    
    public static Image getImage(String URL) {
        return new ImageIcon(Objects.requireNonNull(ImageUtil.class.getResource(URL))).getImage();
    }
    
}
