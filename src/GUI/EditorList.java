package GUI;

import Entities.Entity;
import MapsAndFactories.EntityFactory;
import Models.WorldModel;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * The List with Entities in the EditorPanel
 *
 * @author FlashYoshi
 */
public class EditorList extends JList implements MouseListener {

    private ArrayList<Entity> listData = new ArrayList<>();
    private WorldModel world;
    private int pressedKey;

    public EditorList(WorldModel world) {
        this.world = world;
        setCellRenderer(new CellRenderer());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listData.addAll(Arrays.asList(EntityFactory.getInstance().getAll()));
        setListData(listData.toArray());
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedKey = e.getButton();
    }

    /*Change the cursor and save the selected Entity*/
    @Override
    public void mouseReleased(MouseEvent e) {
        if (pressedKey == MouseEvent.BUTTON1) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Entity selected = (Entity) getSelectedValue();
            Cursor c = toolkit.createCustomCursor(selected.getAvatar(), new Point(0, 0), selected.toString());
            getParent().getParent().setCursor(c);
            getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            world.setCursor(selected);
        } else {
            world.setCursor(null);
            getParent().getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            clearSelection();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
