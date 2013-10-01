package Util;

import Models.WorldModel;
import java.awt.Point;

/**
 *
 * @author FlashYoshi
 */
public class Viewport {

    private final static Viewport viewport = new Viewport();
    public Point start;
    public Point end;
    private WorldModel world;

    private Viewport() {
    }

    public static Viewport getInstance() {
        return viewport;
    }

    public void setViewport(int x1, int y1, int x2, int y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    public void setViewport(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point inViewport(int x, int y) {
        if ((start.x <= x && start.y <= y) && (end.x >= x && end.y >= y)) {
            Point mod = new Point(start.x - end.x, start.y - end.y);
            int paintX = (x % mod.x) * world.getTileSize();
            int paintY = (y % mod.y) * world.getTileSize();
            return new Point(paintX, paintY);
        }
        return null;
    }

    public void setWorld(WorldModel world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return "(" + start.x + ", " + start.y + ") - (" + end.x + ", " + end.y + ")";
    }
}
