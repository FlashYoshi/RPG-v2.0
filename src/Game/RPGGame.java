package Game;

import Actions.OSAction;
import Engine.Game;
import Engine.GamePanel;
import Engine.GameStarter;
import GUI.TList;
import Interfaces.Drawable;
import MapsAndFactories.DrawMap;
import Util.Layer;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Starting area. The game starts up here and the main screen is created
 *
 * @author FlashYoshi
 */
public class RPGGame extends Game {

    private final TList tList;
    private DrawMap drawMap;
    private static final Dimension MIN_SIZE = new Dimension(800, 600);

    public RPGGame(String title) {
        super(title);

        drawMap = DrawMap.getInstance();
        String[] choices = new String[3];
        choices[0] = "Start the Game";
        choices[1] = "Open the Editor";
        choices[2] = "Exit";
        GamePanel panel = new GamePanel(this);
        getFrame().setMinimumSize(MIN_SIZE);
        tList = new TList(panel, choices, new OSAction(this));
        drawMap.addToDraw(tList, Layer.GUI);
        GameStarter.start(this, panel);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        /*Draw everything in order*/
        for (Entry<Layer, ArrayList<Drawable>> entry : drawMap.getToDraw().entrySet()) {
            for (Drawable d : entry.getValue()) {
                d.draw(g);
            }
        }
    }
}
