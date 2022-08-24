package hu.wolfman.fishscreensaver.util;

import java.awt.Image;
import java.util.Objects;
import javax.swing.ImageIcon;

public class ImageUtil {
    
    public static Image getImage(String URL) {
        return new ImageIcon(Objects.requireNonNull(ImageUtil.class.getResource(URL))).getImage();
    }
    
}
