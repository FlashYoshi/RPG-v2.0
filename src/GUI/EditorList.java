package GUI;

import Entities.Entity;
import MapsAndFactories.EntityFactory;
import Models.WorldModel;
import java.awt.Cursor;
import java.awt.Dimension;
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

    public EditorList(Dimension d, WorldModel world) {
        this.world = world;
        setPreferredSize(d);
        setCellRenderer(new CellRenderer());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listData.addAll(Arrays.asList(EntityFactory.getInstance().getAll()));
        setListData(listData.toArray());
        addMouseListener(this);
    }

    /*Change the cursor and save the selected Entity*/
    @Override
    public void mouseClicked(MouseEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Entity selected = (Entity) getSelectedValue();
        Cursor c = toolkit.createCustomCursor(selected.getAvatar(), new Point(0, 0), selected.toString());
        getParent().setCursor(c);
        world.setCursor(selected);
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
}
