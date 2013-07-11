package Entities;

import Util.Layer;

/**
 *
 * @author FlashYoshi
 */
public class Tree extends Entity {

    public Tree() {
        super(Layer.OBSTACLE);
    }

    @Override
    public String toString() {
        return "Tree";
    }
}
