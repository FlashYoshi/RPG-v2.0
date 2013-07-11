package GUI;

import Interfaces.Drawable;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 * WARNING: Be sure you don't need any of your old keylisteners whilst using the
 * TList, it wil temporarily disable them. WARNING: Be sure you don't need the
 * enter key either, this key will also temporarily be disabled.
 * DESTRUCTOR: Don't forget to call detach() when you're done with the list
 *
 * @author FlashYoshi
 */
public class TList extends KeyAdapter implements Drawable {

    private final JPanel panel;
    private final String[] choices;
    private final AbstractAction reaction;
    private final KeyListener[] oldKeyListeners;
    private int selection;
    private static final int FONT_SIZE = 30;
    private static final int GAP = 5;
    private final int offset = (int) (FONT_SIZE * 1.5);
    private final int half;
    private Color selectedColor = Color.BLACK;
    private Color unselectedColor = Color.LIGHT_GRAY;

    /**
     * Creates and draws a nifty list
     *
     * @param panel: Panel to draw the list on.
     * @param choices: All possible things to choose from in the TList.
     * @param reaction: Action executed when a string has been selected. The
     * choice can be found in the ID of the ActionEvent.
     */
    public TList(JPanel panel, String[] choices, AbstractAction reaction) {
        this.panel = panel;
        this.choices = choices;
        this.reaction = reaction;
        half = (int) Math.floor(choices.length / 2);

        selection = 0;
        oldKeyListeners = panel.getKeyListeners();
        for (KeyListener k : oldKeyListeners) {
            panel.removeKeyListener(k);
        }
        panel.addKeyListener(this);
    }

    public TList(JPanel panel, String[] choices, AbstractAction reaction, Color unselectedColor, Color selectedColor) {
        this(panel, choices, reaction);
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;
    }

    public void draw(Graphics2D g) {
        Font font = new Font(g.getFont().getFontName(), 0, FONT_SIZE);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int halfHeight = metrics.getHeight() / 2;
        int mid = panel.getHeight() / 2;

        for (int i = 0; i < choices.length; i++) {
            g.setColor((selection == i) ? selectedColor : unselectedColor);
            String currentChoice = choices[i];
            int x = (panel.getWidth() - metrics.stringWidth(currentChoice)) / 2;
            int y = mid + ((i - half) * offset);
            if (i < half) {
                y -= (half - i) * GAP;
            } else if (i > half) {
                y += ((i - half) * GAP);
            } else {
                y = mid;
            }

            y -= halfHeight;
            g.drawString(currentChoice, x, y);
        }
    }

    public void detach() {
        panel.removeKeyListener(this);
        for (KeyListener k : oldKeyListeners) {
            panel.addKeyListener(k);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                selection = (selection + 1) % choices.length;
                break;
            case KeyEvent.VK_UP:
                selection = (selection == 0) ? choices.length - 1 : selection - 1;
                break;
            case KeyEvent.VK_ENTER:
                reaction.actionPerformed(new ActionEvent(this, selection, "Selection"));
                break;
            default:
                System.err.println("Unkown key pressed: " + e.getKeyCode());
                break;
        }
    }
}
