package Models;

import Util.Viewport;
import java.awt.Dimension;

/**
 *
 * Model used for the editor
 * @param XOffset: The amount of screens we are from the far left edge
 * @param YOffset: The amount of screen we are from the top edge
 * @author FlashYoshi
 */
public class EditorModel extends TModel {

    private int XOffset;
    private int YOffset;
    private int editWidth;
    private int editHeight;
    private int maxWidth;
    private int maxHeight;
    private int change;

    public EditorModel(Dimension worldSize) {
        this.maxWidth = worldSize.width;
        this.maxHeight = worldSize.height;
        XOffset = 0;
        YOffset = 0;
    }

    public void incrementOffset(String s) {
        if (s.equals("x")) {
            incX();
        } else {
            incY();
        }
        Viewport.getInstance().setViewport(getXOffset(), getYOffset(), getXOffset() + editWidth, getYOffset() + editHeight);
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
        System.out.println("(" + getXOffset() + "," + getYOffset() + ") (" + (getXOffset() + editWidth) + ", " + (getYOffset() + editHeight) + ")");
        Viewport.getInstance().setViewport(getXOffset(), getYOffset(), getXOffset() + editWidth, getYOffset() + editHeight);
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
        return XOffset * editWidth;
    }

    public int getYOffset() {
        return YOffset * editHeight;
    }

    public void setEditorDimension(Dimension d) {
        editWidth = d.width;
        editHeight = d.height;
        maxWidth /= editWidth;
        maxHeight /= editHeight;
    }

    public int getChange() {
        return change;
    }
}
