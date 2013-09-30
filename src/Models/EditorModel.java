package Models;

import Engine.Game;
import Util.Viewport;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * Model used for the editor
 *
 * @param XOffset: The amount of screens we are from the far left edge
 * @param YOffset: The amount of screen we are from the top edge
 * @author FlashYoshi
 */
public class EditorModel extends TModel implements ChangeListener {

    private int XOffset;
    private int YOffset;
    private int editWidth;
    private int editHeight;
    private int maxWidth;
    private int maxHeight;
    private int change;
    private Game game;
    private WorldModel world;
    private int stdHeight;
    private int stdWidth;

    public EditorModel(Game game, WorldModel world) {
        this.maxWidth = world.getSize().width;
        this.maxHeight = world.getSize().height;
        this.game = game;
        this.world = world;
        XOffset = 0;
        YOffset = 0;
        world.addListener(this);
    }

    private void changeViewport() {
        Viewport.getInstance().setViewport(getXOffset(), getYOffset(), getXOffset() + editWidth, getYOffset() + editHeight);
        game.setTitle(game.getTitle().split("   ")[0] + "   " + Viewport.getInstance().toString());
    }

    public void incrementOffset(String s) {
        if (s.equals("x")) {
            incX();
        } else {
            incY();
        }
        changeViewport();
    }

    private void incX() {
        if (XOffset + 1 < maxWidth) {
            XOffset++;
            if (XOffset + 1 >= maxWidth) {
                change = 3;
            } else if (XOffset == 1) {
                change = 2;
            } else {
                return;
            }
            fireStateChanged();
        }
    }

    private void incY() {
        if (YOffset + 1 < maxHeight) {
            YOffset++;
            if (YOffset + 1 >= maxHeight) {
                change = 0;
            } else if (YOffset == 1) {
                change = 1;
            } else {
                return;
            }
            fireStateChanged();
        }
    }

    public void decrementOffset(String s) {
        if (s.equals("x")) {
            decX();
        } else {
            decY();
        }
        changeViewport();
    }

    private void decX() {
        if (XOffset != 0) {
            XOffset--;
            if (XOffset == maxWidth - 2) {
                change = 3;
            } else if (XOffset == 0) {
                change = 2;
            } else {
                return;
            }
            fireStateChanged();
        }
    }

    private void decY() {
        if (YOffset != 0) {
            YOffset--;
            if (YOffset == maxHeight - 2) {
                change = 0;
            } else if (YOffset == 0) {
                change = 1;
            } else {
                return;
            }
            fireStateChanged();
        }
    }

    public int getXOffset() {
        return XOffset * (editWidth / world.getZoom());
    }

    public int getYOffset() {
        return YOffset * (editHeight / world.getZoom());
    }

    public void setEditorDimension(Dimension d) {
        editWidth = d.width;
        editHeight = d.height;
        stdWidth = editWidth;
        stdHeight = editHeight;

        maxWidth /= editWidth;
        maxHeight /= editHeight;
    }

    public int getChange() {
        return change;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if ((stdWidth * world.getZoom() < world.getSize().width) && (stdHeight * world.getZoom() < world.getSize().height)) {
            editWidth = stdWidth * world.getZoom();
            editHeight = stdHeight * world.getZoom();
            changeViewport();
        } else {
            world.decZoom();
        }
    }
}
