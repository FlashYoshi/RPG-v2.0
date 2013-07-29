package GUI;

import Actions.ButtonAction;
import Entities.Entity;
import Interfaces.Drawable;
import MapsAndFactories.DrawMap;
import Models.EditorModel;
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
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The gridded panel in editing mode.
 *
 * @author FlashYoshi
 */
public class EditorPanel extends JPanel implements MouseListener, ChangeListener {

    private DrawMap drawModel;
    private WorldModel world;
    private EditorModel buttonModel;
    private JButton[] buttons;
    private Bounds[] bounds;
    private static final int TILE_SIZE = WorldModel.TILE_SIZE;
    private JList list;

    public EditorPanel(Dimension d, WorldModel world, JList list) {
        this.world = world;
        this.list = list;
        setPreferredSize(d);
        drawModel = DrawMap.getInstance();
        buttonModel = new EditorModel(world.getSize());
        setBackground(Color.LIGHT_GRAY);

        int width = (int) Math.ceil((d.width / TILE_SIZE) - 0.1);
        int height = (int) Math.ceil((d.height / TILE_SIZE) - 0.1);
        Viewport.getInstance().setViewport(0, 0, width, height);
        buttonModel.setEditorDimension(new Dimension(d.width / TILE_SIZE, d.height / TILE_SIZE));

        setLayout(null);
        initializeButtons(d);
        addMouseListener(this);
        buttonModel.addListener(this);
    }

    private void initializeButtons(Dimension d) {
        buttons = new JButton[]{new JButton(getArrowSprite("Bottom")),
            new JButton(getArrowSprite("Top")),
            new JButton(getArrowSprite("Left")),
            new JButton(getArrowSprite("Right"))
        };
        bounds = new Bounds[]{new Bounds(d.width / 2, d.height - 32, 64, 32),
            new Bounds(d.width / 2, 0, 64, 32),
            new Bounds(0, (d.height / 2) - 32, 32, 64),
            new Bounds(d.width - 32, (d.height / 2) - 32, 32, 64)
        };
        ButtonAction action = new ButtonAction(buttonModel);

        buttons[1].setEnabled(false);
        buttons[2].setEnabled(false);
        buttons[1].setVisible(false);
        buttons[2].setVisible(false);

        for (int i = 0; i < 4; i++) {
            JButton button = buttons[i];
            add(button);
            button.addActionListener(action);
            button.setContentAreaFilled(false);
            button.setBorder(null);
            Bounds b = bounds[i];
            button.setBounds(b.x, b.y, b.width, b.height);
        }
    }

    private ImageIcon getArrowSprite(String s) {
        return new ImageIcon(EditorPanel.class.getResource("../images/" + s + "Arrow.png"));
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
        int tile = TILE_SIZE;
        for (int i = tile; i < getHeight(); i += tile) {
            g.drawLine(0, i, getWidth(), i);
        }

        for (int i = tile; i < getWidth(); i += tile) {
            g.drawLine(i, 0, i, getHeight());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Entity cursor = world.getCursor();
            if (cursor == null) {
                return;
            }
            int x = (e.getX() / TILE_SIZE) + buttonModel.getXOffset();
            int y = (e.getY() / TILE_SIZE) + buttonModel.getYOffset();
            world.addEntity(cursor.toString(), x, y);
        } else if (e.getButton() == MouseEvent.BUTTON3){
            world.setCursor(null);
            getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            list.clearSelection();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        /*Make the buttons (in)visible and disabled or enabled*/
        JButton button = buttons[buttonModel.getChange()];
        button.setEnabled(!button.isEnabled());
        button.setVisible(!button.isVisible());
    }
}
