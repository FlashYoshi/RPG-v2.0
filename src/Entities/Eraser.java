package Entities;

import Util.Layer;

/**
 *
 * @author Titouan
 */
public class Eraser extends Entity {

    public static final String ERASE = "Eraser";
    
    public Eraser() {
        super(Layer.GUI);
    }
    
    @Override
    public String toString() {
        return ERASE;
    }
}
