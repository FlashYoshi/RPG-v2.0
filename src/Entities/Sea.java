package Entities;

import Util.Layer;

/**
 *
 * @author FlashYoshi
 */
public class Sea extends Entity {

    public Sea() {
        super(Layer.SEA);
    }

    @Override
    public String toString() {
        return "Sea";
    }
}
