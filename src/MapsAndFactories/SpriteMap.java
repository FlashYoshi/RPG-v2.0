package MapsAndFactories;

import Util.SpriteInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * Map which contains the sprites for all the Entities, yet to be used.
 *
 * @author FlashYoshi
 */
public class SpriteMap {

    private static final SpriteMap map = new SpriteMap();
    private Map<String, SpriteInfo> sprites;
    /*A HashMap which maps an object with a Pair which contains the
     * sprite path and the amount of sprite images contained within*/

    private SpriteMap() {
        /*TODO: Add real sprites*/
        sprites = new HashMap<>();
        sprites.put("Grass", new SpriteInfo("../images/Grass.png", 1));
        sprites.put("Sand", new SpriteInfo("../images/Sand.png", 1));
        sprites.put("Sea", new SpriteInfo("../images/Sand.png", 1));
        sprites.put("Tree", new SpriteInfo("../images/Tree.png", 1));
    }

    public SpriteInfo get(String s) {
        return sprites.get(s);
    }

    public static SpriteMap getInstance() {
        return map;
    }
}
