package GUI;

import Entities.Entity;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Cellrenderer for the EditorList.
 *
 * @author FlashYoshi
 */
public class CellRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String s = value.toString();
        setText(s);
        setIcon(new ImageIcon(((Entity) value).getAvatar()));
        setBorder(isSelected ? BorderFactory.createLineBorder(Color.BLACK) : null);
        return this;
    }
}
