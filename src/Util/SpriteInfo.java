package Util;

import Entities.Grass;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Small helper class which holds the amount of sprites in the image and the
 * path to this spriteimage.
 *
 * @author FlashYoshi
 */
public class SpriteInfo {

    public BufferedImage image;
    public int spritesAmount;

    public SpriteInfo(String path, int spritesAmount) {
        try {
            image = ImageIO.read(Grass.class.getResource(path));
            this.spritesAmount = spritesAmount;
        } catch (IOException ex) {
            Logger.getLogger(SpriteInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
