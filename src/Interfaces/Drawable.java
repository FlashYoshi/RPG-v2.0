package Interfaces;

import java.awt.Graphics2D;

/**
 * Interface which every-non-yet-existant object which wishes to be painted
 * should implement
 *
 * @author FlashYoshi
 */
public interface Drawable {

    public void draw(Graphics2D g);
}
