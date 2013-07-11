package MapsAndFactories;

import Interfaces.Drawable;
import Util.Layer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Has an internal map which contains all of the things that have to be drawn.
 * Uses the singleton pattern.
 *
 * @author FlashYoshi
 */
public class DrawMap {

    private static final DrawMap model = new DrawMap();
    private LinkedHashMap<Layer, ArrayList<Drawable>> toDraw = new LinkedHashMap<>();

    /*
     * WARNING: toDraw is a LinkedHashMap. This means that it works with
     * insertion sort. Altering the order of puts will alter the way things
     * are drawn onto the screen.
     */
    private DrawMap() {
        toDraw.put(Layer.SEA, new ArrayList<Drawable>());
        toDraw.put(Layer.BACKGROUND, new ArrayList<Drawable>());
        toDraw.put(Layer.OBSTACLE, new ArrayList<Drawable>());
        toDraw.put(Layer.GUI, new ArrayList<Drawable>());
    }

    public static DrawMap getInstance() {
        return model;
    }

    public LinkedHashMap<Layer, ArrayList<Drawable>> getToDraw() {
        return toDraw;
    }

    public ArrayList<Drawable> getToDraw(Layer l) {
        return toDraw.get(l);
    }

    public void addToDraw(Drawable d, Layer l) {
        toDraw.get(l).add(d);
    }

    public void removeToDraw(Drawable d, Layer l) {
        toDraw.get(l).remove(d);
    }

    public void reset(){
        for (Map.Entry<Layer, ArrayList<Drawable>> entry : toDraw.entrySet()){
            entry.getValue().clear();
        }
    }
}
