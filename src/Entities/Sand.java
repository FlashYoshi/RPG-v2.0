package Entities;

import Util.Layer;

/**
 *
 * @author FlashYoshi
 */
public class Sand extends Entity {

    public Sand() {
        super(Layer.BACKGROUND);
    }

    @Override
    public String toString() {
        return "Sand";
    }
}
