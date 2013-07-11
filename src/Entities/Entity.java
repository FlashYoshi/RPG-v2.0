package Entities;

import Interfaces.Drawable;
import Util.Layer;
import Util.Viewport;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * Representation of any and all "Blocks"
 *
 * @author FlashYoshi
 */
public class Entity implements Drawable {

    private Point pos;
    /*TODO: Delete the placeholder*/
    private Image image = new ImageIcon(Grass.class.getResource("../images/Sand.png")).getImage();
    /*Counter for the sprite images*/
    private int spriteCount;
    protected boolean breakable = false;
    private Layer layer;

    public Entity(Layer layer) {
        this.layer = layer;
        pos = new Point(0,0);
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }

    public Point getPosition() {
        return pos;
    }

    public void setPosition(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    public void setPosition(Point pos) {
        this.pos = pos;
    }

    @Override
    public void draw(Graphics2D g) {
        Point p;
        /*Check if we really have to draw*/
        if ((p = Viewport.getInstance().inViewport(pos.x, pos.y)) != null) {
            g.drawImage(getSprite(), p.x, p.y, null);
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Layer identify(){
        return layer;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public Image getAvatar() {
        /*TODO: return first sprite*/
        return image;
    }

    private Image getSprite() {
        /*TODO: Return sprite*/
        /*if (SpriteMap.getInstance().get(toString()).spritesAmount == spriteCount) {
            spriteCount = 0;
        } else {
            spriteCount++;
        }*/
        return image;
    }

    @Override
    public String toString() {
        return "Entity";
    }
}
