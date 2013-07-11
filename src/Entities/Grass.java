package Entities;

import Util.Layer;

/**
 *
 * @author FlashYoshi
 */
public class Grass extends Entity {

    public Grass(){
        super(Layer.BACKGROUND);
    }

    @Override
    public String toString() {
        return "Grass";
    }
}
