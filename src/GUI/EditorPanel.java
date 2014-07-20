package GUI;

import Actions.ButtonAction;
import Engine.Game;
import Entities.Entity;
import Interfaces.Drawable;
import MapsAndFactories.DrawMap;
import Models.ButtonModel;
import Models.WorldModel;
import Util.Layer;
import Util.Viewport;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * The gridded panel in editing mode.
 *
 * @author FlashYoshi
 */
public class EditorPanel extends JPanel implements MouseListener, MouseMotionListener {

    private DrawMap drawModel;
    private WorldModel world;
    private ButtonModel buttonModel;
    private JButton[] buttons;
    private Bounds[] bounds;
    private JList list;
    private int prevX;
    private int prevY;
    private int pressedKey;

    public EditorPanel(Dimension d, WorldModel world, JList list, Game game) {
        this.world = world;
        this.list = list;
        setPreferredSize(d);
        drawModel = DrawMap.getInstance();
        buttonModel = new ButtonModel(d, world, game);
        setBackground(Color.LIGHT_GRAY);

        buttonModel.changeViewport();
        world.setButtonModel(buttonModel);
        world.setViewableSize(d);

        setLayout(null);
        initializeButtons(d);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void initializeButtons(Dimension d) {
        buttons = new JButton[]{new JButton(getButtonSprite("BottomArrow")),
            new JButton(getButtonSprite("TopArrow")),
            new JButton(getButtonSprite("LeftArrow")),
            new JButton(getButtonSprite("RightArrow")),
            new JButton(getButtonSprite("-")),
            new JButton(getButtonSprite("+"))};

        bounds = new Bounds[]{new Bounds(d.width / 2, d.height - 32, 64, 32),
            new Bounds(d.width / 2, 0, 64, 32),
            new Bounds(0, (d.height / 2) - 32, 32, 64),
            new Bounds(d.width - 32, (d.height / 2) - 32, 32, 64),
            new Bounds(d.width - 32, (d.height / 4) - 32, 32, 32),
            new Bounds(d.width - 32, (d.height / 4) - 64, 32, 32)};

        ButtonAction action = new ButtonAction(buttonModel, world);

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            add(button);
            button.addActionListener(action);
            button.setContentAreaFilled(false);
            button.setBorder(null);
            Bounds b = bounds[i];
            button.setBounds(b.x, b.y, b.width, b.height);
        }
    }

    private ImageIcon getButtonSprite(String s) {
        return new ImageIcon(EditorPanel.class.getResource("../images/" + s + ".png"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*Draw everything in order*/
        drawGrid(g);
        for (Map.Entry<Layer, ArrayList<Drawable>> entry : drawModel.getToDraw().entrySet()) {
            for (Drawable d : entry.getValue()) {
                d.draw((Graphics2D) g);
            }
        }
    }

    private void drawGrid(Graphics g) {
        int tile = world.getTileSize();
        for (int i = tile; i < getHeight(); i += tile) {
            g.drawLine(0, i, getWidth(), i);
        }

        for (int i = tile; i < getWidth(); i += tile) {
            g.drawLine(i, 0, i, getHeight());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedKey = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedKey = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int x = (e.getX() / world.getTileSize()) + buttonModel.getXOffset();
            int y = (e.getY() / world.getTileSize()) + buttonModel.getYOffset();
            addEntity(x, y);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            resetCursor();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressedKey == MouseEvent.BUTTON1) {
            int x = (e.getX() / world.getTileSize()) + buttonModel.getXOffset();
            int y = (e.getY() / world.getTileSize()) + buttonModel.getYOffset();

            if ((x != prevX || y != prevY)) {
                addEntity(x, y);
                prevX = x;
                prevY = y;
            }
        }else if (pressedKey == MouseEvent.BUTTON3) {
            resetCursor();
        }
    }

    private void addEntity(int x, int y) {
        Entity cursor = world.getCursor();
        if (cursor == null) {
            return;
        }

        Viewport viewport = Viewport.getInstance();
        if ((x <= viewport.end.x && y <= viewport.end.y)
                && (x >= viewport.start.x && y >= viewport.start.y)) {
            world.addEntity(cursor.toString(), x, y);
        }
    }
    
    private void resetCursor() {
            world.setCursor(null);
            getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            list.clearSelection();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
