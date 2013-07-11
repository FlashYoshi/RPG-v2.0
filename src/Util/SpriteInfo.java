package Util;

/**
 * Small helper class which holds the amount of sprites in the image and the
 * path to this spriteimage.
 *
 * @author FlashYoshi
 */
public class SpriteInfo {

    public String path;
    public int spritesAmount;

    public SpriteInfo(String path, int spritesAmount) {
        this.path = path;
        this.spritesAmount = spritesAmount;
    }
}
