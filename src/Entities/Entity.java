package Entities;

import Interfaces.Drawable;
import Models.WorldModel;
import Util.Layer;
import Util.Viewport;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Representation of any and all "Blocks"
 *
 * @author FlashYoshi
 */
public class Entity implements Drawable {

    private Point pos;
    /*TODO: Delete the placeholder*/
    private BufferedImage image;
    /*Counter for the sprite images*/
    private int spriteCount;
    protected boolean breakable = false;
    private Layer layer;

    public Entity(Layer layer) {
        try {
            image = ImageIO.read(Grass.class.getResource("../images/Sand.png"));
            this.layer = layer;
            pos = new Point(0, 0);
        } catch (IOException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            int zoom = 1;
            int zoomedSize = WorldModel.TILE_SIZE / zoom;
            
            /*Rescale the image depending on the zoomfactor*/
            Image img = getSprite().getScaledInstance(zoomedSize, zoomedSize, Image.SCALE_DEFAULT);
            g.drawImage(img, p.x, p.y, null);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Layer identify() {
        return layer;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public Image getAvatar() {
        /*TODO: return first sprite*/
        return image;
    }

    private BufferedImage getSprite() {
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
