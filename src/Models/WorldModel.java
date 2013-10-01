package Models;

import Util.Layer;
import MapsAndFactories.EntityFactory;
import Entities.Entity;
import Entities.Sea;
import MapsAndFactories.DrawMap;
import Util.Viewport;
import java.awt.Dimension;
import java.util.HashMap;

/**
 * Main model of the game. It represents the entire world.
 *
 * @author FlashYoshi
 */
public class WorldModel {

    public static final int STD_TILE_SIZE = 64;
    private EntityFactory entities;
    private Entity[][] background;
    private Entity[][] obstacles;
    private Sea[][] sea;
    private Dimension world;
    private HashMap<Layer, Entity[][]> layers;
    private Entity selected;
    private int tileSize;
    private Dimension viewableSize;
    private ButtonModel buttonModel;

    public WorldModel(int width, int height) {
        this(new Dimension(width, height));
    }

    public WorldModel(Dimension worldSize) {
        tileSize = STD_TILE_SIZE;
        if (worldSize.width % tileSize != 0
                || worldSize.height % tileSize != 0) {
            throw new IllegalArgumentException("Dimension has to be devisable by " + tileSize + ".");
        }
        this.world = worldSize;
        background = new Entity[worldSize.height][worldSize.width];
        obstacles = new Entity[worldSize.height][worldSize.width];
        sea = new Sea[worldSize.height][worldSize.width];

        entities = EntityFactory.getInstance();
        layers = new HashMap<>();
        layers.put(Layer.SEA, sea);
        layers.put(Layer.BACKGROUND, background);
        layers.put(Layer.OBSTACLE, obstacles);
        Viewport.getInstance().setWorld(this);
    }

    public int getTileSize() {
        return tileSize;
    }

    public void zoomIn() {
        if (tileSize < STD_TILE_SIZE) {
            tileSize *= 2;
            buttonModel.changeViewport();
        }
    }

    public void zoomOut() {
        int xTiles = viewableSize.width / tileSize;
        int yTiles = viewableSize.height / tileSize;
        if ((xTiles * 2) + buttonModel.getXOffset() < world.width
                && (yTiles * 2) + buttonModel.getYOffset() < world.height) {
            tileSize /= 2;
            buttonModel.changeViewport();
        }
    }

    public Dimension getSize() {
        return world;
    }

    public Sea[][] getSea() {
        return sea;
    }

    public Entity[][] getBackground() {
        return background;
    }

    public Entity[][] getObstacles() {
        return obstacles;
    }

    /*Adds an entity to the corresponding layer and adds it to the draw list if
     * needed.
     * @return: returns wether or not the entity was added.
     */
    public boolean addEntity(String id, int x, int y) {
        Entity e = entities.get(id);
        if (e == null) {
            return false;
        }
        e.setPosition(x, y);
        boolean result;

        if (result = checkAvailability(e)) {
            Layer layer = e.identify();
            layers.get(layer)[x][y] = e;
            DrawMap.getInstance().addToDraw(e, layer);
            e.setModel(this);
        }
        return result;
    }

    public Entity getCursor() {
        return selected;
    }

    public void setCursor(Entity selected) {
        this.selected = selected;
    }

    private boolean checkAvailability(Entity e) {
        int x = e.getX();
        int y = e.getY();
        if (x < world.width && y < world.height) {
            Layer layer = e.identify();
            Entity placeHolder = layers.get(layer)[x][y];
            if ((layer == Layer.SEA && layers.get(Layer.SEA)[x][y] == null)
                    || (layer == Layer.BACKGROUND && placeHolder == null)/*Spot not taken*/
                    || (layer == Layer.OBSTACLE && (layers.get(Layer.BACKGROUND)[x][y] != null && placeHolder == null))/*There is a background but no obstacle yet*/
                    || layer == Layer.GUI) {
                return true;
            }
        }
        return false;
    }

    public void setViewableSize(Dimension viewableSize) {
        this.viewableSize = viewableSize;
    }

    public void setButtonModel(ButtonModel model) {
        this.buttonModel = model;
    }
}
