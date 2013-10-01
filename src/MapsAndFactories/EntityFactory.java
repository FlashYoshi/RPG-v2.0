package MapsAndFactories;

import Entities.Entity;
import Entities.Grass;
import Entities.Sand;
import Entities.Sea;
import Entities.Tree;

/**
 * Factory for Entities. Uses the singleton pattern.
 *
 * @author FlashYoshi
 */
public class EntityFactory {

    private static final EntityFactory fac = new EntityFactory();

    private EntityFactory() {
    }

    public Entity get(String s) {
        /*TODO: Add more entities*/
        switch (s) {
            case "Sea":
                return new Sea();
            case "Grass":
                return new Grass();
            case "Sand":
                return new Sand();
            case "Tree":
                return new Tree();
            default:
                return null;
        }
    }

    public Entity[] getAll() {
        return new Entity[]{new Sea(), new Grass(), new Sand(), new Tree()};
    }

    public static EntityFactory getInstance() {
        return fac;
    }
}
