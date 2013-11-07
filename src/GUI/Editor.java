package GUI;

import Actions.LevelMaintenance.LoadLevel;
import Actions.LevelMaintenance.SaveLevel;
import Engine.Game;
import Models.WorldModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Wrapper class for the EditorPanel and EditorList
 *
 * @author FlashYoshi, Mathieu De Coster
 */
public class Editor {

    public Editor(JPanel panel, Game game) {
        int selectedDimensionX = 128;
        int selectedDimensionY = 128;
        /* Display dimension selection screen */
        JTextField fieldX = new JTextField(5);
        JTextField fieldY = new JTextField(5);

        JPanel dimPanel = new JPanel();
        dimPanel.add(fieldX);
        dimPanel.add(Box.createHorizontalStrut(5));
        dimPanel.add(new JLabel("x"));
        dimPanel.add(Box.createHorizontalStrut(5));
        dimPanel.add(fieldY);

        int result = JOptionPane.showConfirmDialog(null, dimPanel,
                "Enter preferred dimensions", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                selectedDimensionX = Integer.parseInt(fieldX.getText());
            } catch(NumberFormatException ex) {
                System.err.println("X dimension is not a valid number. " + 
                        "Picking default (" + selectedDimensionX + ").");
            }
            try {
                selectedDimensionY = Integer.parseInt(fieldY.getText());
            } catch (NumberFormatException ex) {
                System.err.println("Y dimension is not a valid number. " + 
                        "Picking default (" + selectedDimensionY + ").");
            }
        }

        JFrame frame = (JFrame) panel.getParent().getParent().getParent();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        Dimension panelSize = panel.getSize();
        WorldModel world = new WorldModel(selectedDimensionX, selectedDimensionY);

        Dimension d = new Dimension((int) (panelSize.width * 0.2), 52);
        JPanel listPanel = new JPanel();
        listPanel.setMinimumSize(new Dimension(d.width, panel.getHeight()));
        listPanel.setLayout(new BorderLayout());
        EditorList list = new EditorList(world);

        JButton save = new JButton("Save");
        save.addActionListener(new SaveLevel(world));
        JButton load = new JButton("Load");
        load.addActionListener(new LoadLevel(world));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(d);
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(save, BorderLayout.NORTH);
        buttonPanel.add(load, BorderLayout.CENTER);

        listPanel.add(buttonPanel, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        EditorPanel editPanel = new EditorPanel(new Dimension((int) (panelSize.width * 0.8), panelSize.height), world, list, game);
        panel.add(editPanel);
        panel.add(listPanel);

        frame.setResizable(false);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
