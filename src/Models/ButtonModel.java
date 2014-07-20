package Models;

import Engine.Game;
import Util.Viewport;
import java.awt.Dimension;

/**
 *
 * Model used for the editor
 *
 * @var XOffset: The amount of screens we are from the far left edge
 * @var YOffset: The amount of screens we are from the top edge
 * @author FlashYoshi
 */
public class ButtonModel {

    /*Amount of tiles not being shown on the left side.*/
    private int XOffset;
    /*Amount of tiles not being shown on the top.*/
    private int YOffset;
    private Game game;
    private WorldModel world;
    private Dimension panelSize;

    public ButtonModel(Dimension panelSize, WorldModel world, Game game) {
        this.panelSize = panelSize;
        this.world = world;
        this.game = game;
        XOffset = 0;
        YOffset = 0;
    }

    public void changeViewport() {
        int xTiles = panelSize.width / world.getTileSize();
        int yTiles = panelSize.height / world.getTileSize();
        Viewport.getInstance().setViewport(getXOffset(), getYOffset(), getXOffset() + xTiles, getYOffset() + yTiles);
        game.setTitle(game.getTitle().split("   ")[0] + "   " + Viewport.getInstance().toString());
    }

    public void Xscroll(int side) {
        if (checkXscroll(side)) {
            XOffset += side * panelSize.width / world.getTileSize();
            if (XOffset < 0) {
                XOffset = 0;
            }
        }
        changeViewport();
    }

    private boolean checkXscroll(int side) {
        /*The amount of viewable tiles on the x axis*/
        int xTiles = panelSize.width / world.getTileSize();
        int maxX = world.getSize().width;
        if (side == 1) {
            if (XOffset == 0 && xTiles * 2 < maxX) {
                return true;
            } else return xTiles * 2 + XOffset < maxX;
        } else {
            return XOffset != 0;
        }
    }

    public void Yscroll(int side) {
        if (checkYscroll(side)) {
            YOffset += side * panelSize.height / world.getTileSize();
            if (YOffset < 0) {
                YOffset = 0;
            }
        }
        changeViewport();
    }

    private boolean checkYscroll(int side) {
        /*The amount of viewable tiles on the x axis*/
        int yTiles = panelSize.height / world.getTileSize();
        int maxY = world.getSize().height;
        if (side == 1) {
            if (YOffset == 0 && yTiles * 2 < maxY) {
                return true;
            } else return yTiles * 2 + YOffset < maxY;
        } else {
            return YOffset != 0;
        }
    }

    public int getXOffset() {
        return XOffset;
    }

    public int getYOffset() {
        return YOffset;
    }
}
